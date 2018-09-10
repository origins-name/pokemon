<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		<script type="text/javascript" src="<%=path %>/js/public.js"></script>
        <script language="javascript">
           function baomingDelByAd(baomingId)
           {
               if(confirm('您确定删除吗？'))
               {
                     var url="<%=path %>/baomingDelByAd.action?baomingId="+baomingId;
	                 var n="";
	                 var w="300px";
	                 var h="200px";
	                 var s="resizable:no;help:no;status:no;scroll:yes";
					 openWin(url,n,w,h,s);
               }
           }
           
           function baomingTongguo(baomingId)
           {
                     var url="<%=path %>/baomingTongguo.action?baomingId="+baomingId;
	                 var n="";
	                 var w="300px";
	                 var h="200px";
	                 var s="resizable:no;help:no;status:no;scroll:yes";
					 openWin(url,n,w,h,s);
           }
           
           function baomingJujue(baomingId)
           {
                 var url="<%=path %>/baomingJujue.action?baomingId="+baomingId;
                 var n="";
                 var w="300px";
                 var h="200px";
                 var s="resizable:no;help:no;status:no;scroll:yes";
				 openWin(url,n,w,h,s);
           }
           
           function yundongyuanDetail(yundongyuanId)
           {
                 var url="<%=path %>/yundongyuanDetail.action?id="+yundongyuanId;
                 var n="";
                 var w="480px";
                 var h="500px";
                 var s="resizable:no;help:no;status:no;scroll:yes";
				 openWin(url,n,w,h,s);
           }
           function p()
           {
              window.print();
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="4" background="<%=path %>/images/tbg.gif">&nbsp;报名管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="25%">报名时间</td>
					<td width="25%">运动员</td>
					<td width="25%">审核状态</td>
					<td width="25%">操作</td>
		        </tr>	
				<s:iterator value="#request.baomingList" id="baoming">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#baoming.baomingShijian"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="#" style="color: red" onclick="yundongyuanDetail(<s:property value="#baoming.yundongyuanId"/>)"><s:property value="#baoming.yundongyuanName"/></a>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#baoming.baomingZhuangtai"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="#" onclick="baomingDelByAd(<s:property value="#baoming.baomingId"/>)" class="pn-loperator">删除</a>
					    <a href="#" onclick="baomingTongguo(<s:property value="#baoming.baomingId"/>)" class="pn-loperator">通过</a>
					    <a href="#" onclick="baomingJujue(<s:property value="#baoming.baomingId"/>)" class="pn-loperator">拒绝</a>
					</td>
				</tr>
				</s:iterator>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="打印" style="width: 80px;" onclick="p()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
