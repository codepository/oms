package com.lt.cloud.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lt.cloud.mapper.OrgMapper;
import com.lt.cloud.pojo.Org;
import com.lt.cloud.pojo.PowerNode;
import com.lt.cloud.service.OrgService;
import com.lt.cloud.utils.JsonUtils;
import com.lt.cloud.utils.ResponseCodeUtils;
@Component
public class OrgServiceImpl implements OrgService{
	@Autowired
	private OrgMapper mapper;
	@Override
	public String save(Org receiver) {
		try {
			if (this.mapper.existsByLabel(receiver.getLabel())) {
				return ResponseCodeUtils.responseErr("已经存在");
			}
			this.mapper.save(receiver);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.err(e);
		}
		return ResponseCodeUtils.responseOk(receiver.getId().toString());
	}
	@Override
	public String deleteById(Integer id) {
		try {
			this.mapper.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.err(e);
		}
		return ResponseCodeUtils.responseOk("成功");
	}
	@Override
	public String findAll() {
		List<Org> result=null;
		try {
			result=this.mapper.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.err(e);
		}
		try {
			return this.transform2Tree(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtils.err(e);
		}
	}
	private String transform2Tree(List<Org> list) {
    	if (list==null||list.size()==0) {
			return null;
		}
    	// 获取节点 最小值和最大值
    	Integer max=0;
    	Integer min=list.get(0).getDepth();
    	List<PowerNode> compos=new ArrayList<>();
    	for (Org tree:list) {
    		if (min>tree.getDepth()) {
				min=tree.getDepth();
			}
    		if (max<tree.getDepth()) {
				max=tree.getDepth();
			}
    		PowerNode child=new PowerNode();
    		child.setDepth(tree.getDepth());
    		child.setLabel(tree.getLabel());
    		child.setParentid(tree.getParentid());
    		child.setValue(tree.getId());
    		compos.add(child);
    	}
//    	System.out.println("----min:"+min+",max:"+max);
    	// 按深度分组
    	Map<Integer, List<PowerNode>> groupMap=compos.stream().collect(Collectors.groupingBy(PowerNode::getDepth));
//    	System.out.println("-------------------深度分组---------------");
//    	System.out.println(JsonUtils.getGson().toJson(groupMap));
    	List<PowerNode> result = findParents(groupMap, groupMap.get(max), max, min);
//    	System.out.println("--------------------结果------------------");
//    	System.out.println(JsonUtils.getGson().toJson(result));
    	return JsonUtils.getGson().toJson(result);
    	
    }
    /**
     * groupMap 为按深度depth分组的Map<Integer, List<PowerCompo>
     * @param groupMap
     * @param current
     * @param currentdepth
     * @param minDepth
     * @return
     */
    private List<PowerNode> findParents(Map<Integer, List<PowerNode>> groupMap, List<PowerNode> current,Integer currentdepth,Integer minDepth) {
    	if (currentdepth==minDepth) {
			return current;
		}
    	// 寻找父节点，并将子节点放入父节点
    	for (PowerNode tree:current) {
    		for(PowerNode parent:groupMap.get(currentdepth-1)) {
//    			if (tree.getId() == 203) {
//    				System.out.println("---------------父节点:"+parent.getTitle()+",id="+parent.getId());
//    			}
    			// long 对比必须用equals
    			if (tree.getParentid()==parent.getValue()) {// 寻找父节点
//    				if (tree.getId()==203) {
//						System.out.println(tree.getTitle()+" 找到父节点"+parent.getTitle());
//					}
					parent.getChildren().add(tree);
					break;
				}
    		}
    	}
    	// 设置当前深度
    	currentdepth--;
    	// 设置当前深度最小组
    	current=groupMap.get(currentdepth);
    	return findParents(groupMap, current, currentdepth, minDepth);
    }

}
