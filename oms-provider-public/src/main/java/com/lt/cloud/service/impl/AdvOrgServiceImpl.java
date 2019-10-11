package com.lt.cloud.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.stereotype.Component;

import com.lt.cloud.mapper.AdvOrgMapper;
import com.lt.cloud.pojo.AdvOrg;
import com.lt.cloud.pojo.AdvTrade;
import com.lt.cloud.pojo.Trade;
import com.lt.cloud.service.AdvOrgService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;
import com.sun.rowset.internal.Row;

import ch.qos.logback.core.pattern.parser.Node;
import net.sf.jsqlparser.statement.select.Top;
@Component
public class AdvOrgServiceImpl implements AdvOrgService{
	@Autowired
	private AdvOrgMapper advOrgMapper;
	@Override
	public List<AdvOrg> selectAllAdvOrg(HashMap<String, Object> params) {
		
		return this.advOrgMapper.selectAllAdvOrg(params);
	}
	@Override
	public List<AdvTrade> selectAllTrade(HashMap<String, Object> params) {
		
		return this.advOrgMapper.selectAllTrade(params);
	}
	@Override
	public List<Trade> findAllTrade(HashMap<String, Object> params) {
		
		return this.advOrgMapper.findAllTrade(params);
	}
	@Override
	public String buildTradeJSONTree() {
		List<Trade> trades=this.findAllTrade(new HashMap<>());
		
		return transformToTreeJson(trades);
	}
	@Override
	public String buildTradeJSONTree(String username) {
		List<Trade> trades=this.findAllTradeByUser(username);
		return transformToTreeJson(trades);
	}
	private String transformToTreeJson(List<Trade> list) {
		if (list==null||list.size()==0) {
			return null;
		}
		List<Trade> result=new ArrayList<>();
		// 获取节点深度最小值 （深度越小越上层）
		Integer depth = list.stream().min(Comparator.comparing(Trade::getDepth)).get().getDepth();
		// 按父节点分组
		Map<Integer, List<Trade>> groupByParentid = list.stream().collect(Collectors.groupingBy(Trade::getParentid));
		// 将同组中的子节点存入父节点
		List<Trade> temp=new ArrayList<Trade>();
		groupByParentid.entrySet().stream().forEach(group ->{
			Trade parent=new Trade();
			parent.setId(group.getKey());
			parent.setDepth(group.getValue().get(0).getDepth());
			group.getValue().stream().forEach(trade ->{
				parent.add(trade);
			});
			temp.add(parent);
		});
		temp.stream().sorted(Comparator.comparing(Trade::getDepth).reversed()).filter(node->{
			// 将当前节点存入上层节点
			temp.stream().filter(node1 ->{ // 找到当前节点上层的所有节点（可能有多个)
				return node.getDepth()-1 >=depth && node.getDepth()-1 == node1.getDepth();
			}).forEach(topNode ->{//在所有可能的上层节点中找到属于当前节点的上层节点
				topNode.getChildren().stream().forEach(child ->{
					if (child.getId() == node.getId()) { //在上一层的节点里找到对应的节点,并将节点存入上一层的节点中
						child.getChildren().addAll(node.getChildren());
					}
				});
			});
			return node.getDepth()==depth; //最终返回深度最浅的元素
		}).forEach(row -> {
			result.addAll(0, row.getChildren());
		});
		
		
		return JsonUtils.getGson().toJson(result);
	}
	@Override
	public String save(Trade trade) {
		if (this.existsByText(trade.getText())) {
			return ResponseCodeUtils.responseErr("已经存在行业【"+trade.getText()+"】，添加失败");
		}
		trade.setLabel(trade.getText());
		try {
			this.advOrgMapper.save(trade);
			return ResponseCodeUtils.responseOk(""+trade.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.err(e);
		}
	}
	@Override
	public String deleteById(int id) {
		try {
			// 查询是否存在子节点
			if(this.existsChildById(id)) {
				return ResponseCodeUtils.responseErr("节点【"+id+"】存在下级行业无法删除，请先删除下级行业");
			}
			// 删除
			this.advOrgMapper.deleteById(id);
			return ResponseCodeUtils.responseOk("成功");
		} catch (Exception e) {
			return ResponseCodeUtils.err(e);
		}
	}
	@Override
	public boolean existsChildById(int id) {
		
		return this.advOrgMapper.existsChildById(id);
	}
	@Override
	public boolean existsByText(String text) {
	
		return this.advOrgMapper.existsByText(text);
	}
	@Override
	public List<Trade> findAllTradeByUser(String username) {
		
		return this.advOrgMapper.findAllTradeByUser(username);
	}

}
