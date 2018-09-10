package com.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TBaomingDAO;
import com.dao.TCatelogDAO;
import com.dao.TYundongyuanDAO;
import com.model.TBaoming;
import com.model.TYundongyuan;
import com.opensymphony.xwork2.ActionSupport;

public class baomingAction extends ActionSupport
{
	private int baomingId;
	private int catelogId;
	private TBaomingDAO baomingDAO;
	private TCatelogDAO catelogDAO;
	private TYundongyuanDAO yundongyuanDAO;
	
	private String message;
	private String path;
	
	
	public String baomingAdd()
	{
		Map session= ServletActionContext.getContext().getSession();
		TYundongyuan yundongyuan=(TYundongyuan)session.get("yundongyuan");
		
		String sql="from TBaoming where catelogId=? and yundongyuanId=?";
		Object[] c={catelogId,yundongyuan.getId()};
		List list=baomingDAO.getHibernateTemplate().find(sql,c);
		if(list.size()>0)
		{
			this.setMessage("您已经报名过。请不要重复报名");
			this.setPath("catelogAll.action");
			return "succeed";
		}
		
		String sql1="from TBaoming where yundongyuanId=?";
		Object[] c1={yundongyuan.getId()};
		List list1=baomingDAO.getHibernateTemplate().find(sql1,c1);
		if(list1.size()>3)
		{
			this.setMessage("报名项目不能超过4个");
			this.setPath("catelogAll.action");
			return "succeed";
		}
		
		
		TBaoming baoming=new TBaoming();
		baoming.setBaomingShijian(new Date().toLocaleString());
		baoming.setCatelogId(catelogId);
		baoming.setYundongyuanId(yundongyuan.getId());
		baoming.setBaomingZhuangtai("审核中");//"shenpizhong","pass"."fail"
		baomingDAO.save(baoming);
		this.setMessage("报名完毕");
		this.setPath("catelogAll.action");
		return "succeed";
	}
	
	
	
	
	public String baomingMy()
	{
		Map session= ServletActionContext.getContext().getSession();
		TYundongyuan yundongyuan=(TYundongyuan)session.get("yundongyuan");
		
		String sql="from TBaoming where yundongyuanId=?";
		Object[] c={yundongyuan.getId()};
		List baomingList=baomingDAO.getHibernateTemplate().find(sql,c);
		for(int i=0;i<baomingList.size();i++)
		{
			TBaoming baoming=(TBaoming)baomingList.get(i);
			baoming.setCatelogName(catelogDAO.findById(baoming.getCatelogId()).getCatelogName());
		}
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("baomingList", baomingList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String baomingDelByMy()
	{
		TBaoming baoming=baomingDAO.findById(baomingId);
		baomingDAO.delete(baoming);
		this.setMessage("操作成功");
		this.setPath("baomingMy.action");
		return "succeed";
	}
	
	
	
	public String baomingMana()
	{
		
		String sql="from TBaoming where catelogId=?";
		Object[] c={catelogId};
		List baomingList=baomingDAO.getHibernateTemplate().find(sql,c);
		for(int i=0;i<baomingList.size();i++)
		{
			TBaoming baoming=(TBaoming)baomingList.get(i);
			baoming.setYundongyuanName(yundongyuanDAO.findById(baoming.getYundongyuanId()).getName());
		}
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("baomingList", baomingList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String baomingDelByAd()
	{
		TBaoming baoming=baomingDAO.findById(baomingId);
		baomingDAO.delete(baoming);
		return "successDel";
	}
	
	
	public String baomingTongguo()
	{
		TBaoming baoming=baomingDAO.findById(baomingId);
		baoming.setBaomingZhuangtai("审核通过。");
		baomingDAO.attachDirty(baoming);
		return "successDel";
	}
	
	public String baomingJujue()
	{
		TBaoming baoming=baomingDAO.findById(baomingId);
		baoming.setBaomingZhuangtai("审核不通过");
		baomingDAO.attachDirty(baoming);
		return "successDel";
	}
	
	
	
	public TBaomingDAO getBaomingDAO()
	{
		return baomingDAO;
	}
	public void setBaomingDAO(TBaomingDAO baomingDAO)
	{
		this.baomingDAO = baomingDAO;
	}
	public int getCatelogId()
	{
		return catelogId;
	}
	public void setCatelogId(int catelogId)
	{
		this.catelogId = catelogId;
	}
	
	public TYundongyuanDAO getYundongyuanDAO()
	{
		return yundongyuanDAO;
	}




	public void setYundongyuanDAO(TYundongyuanDAO yundongyuanDAO)
	{
		this.yundongyuanDAO = yundongyuanDAO;
	}




	public String getMessage()
	{
		return message;
	}
	
	public int getBaomingId()
	{
		return baomingId;
	}


	public void setBaomingId(int baomingId)
	{
		this.baomingId = baomingId;
	}


	public TCatelogDAO getCatelogDAO()
	{
		return catelogDAO;
	}


	public void setCatelogDAO(TCatelogDAO catelogDAO)
	{
		this.catelogDAO = catelogDAO;
	}


	public void setMessage(String message)
	{
		this.message = message;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}

}
