<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="Database.UserData" %>
    <% String id =""+(Integer)session.getAttribute("id"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
     <link rel="stylesheet" type="text/css" href="css/mview.css" />
	<style type="text/css">
	body, html{width: 100%;height:100%;font-family:"微软雅黑";}
	#allmap{width: 100%;height:350px;overflow: hidden;position:absolute;left:0;top:50px;font-family:"微软雅黑";}
	optionpanel{margin: 10px;}
	#r-result{width:100%; position:absolute;left:20px;top:410px;z-index:1;}
	#r-result p{margin:5px 0 0 10px;}
	</style>
	<script type="text/javascript" src="js/view.js"></script>
    <script type="text/javascript">
	function lefthitten(element){
		var oDiv=document.getElementById(element);
		startMove(oDiv,{left:-300},function() {
			oDiv.style.display='none';
		});
	}
	</script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=A4D8TErDKWcaXsZUY3RMGMFSyNv8OKBD"></script>
	<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
	<title>WhoWhere</title>
</head>
<body>
	<div id="pageheader" >
    	<img class="logo" src="img/logo.png" />
       	<a id="menuebutton" class="menuebutton" href="javascript:" onClick="showmenuepanel()">菜单</a>
        <ul class="menuelist" id="menuelist" value="0">
			<li><a id="groupshow" href="javascript:" onClick="leftshow('group')">群聊天</a></li>
			<li><a id="personlistshow" href="javascript:" onClick="requestUser('user')">用户列表</a></li>
            <li><a href="javascript:" onClick="getLocation()">重定位</a></li>
			<li><a id="personlistshow" href="javascript:" onClick="showSuggest()">使用手册</a></li>			
			<li> 
			<% if(id.equals("null"))
					out.print(" <a id='signout' class='signout' href='login.jsp'>登录</a>");
				else
					out.print(" <a id='signout' class='signout' href='signout'>退 出</a>");
			%></li>
        </ul>
    </div>
	<div id="allmap"></div>
	<div id="r-result">
		<div class="optionpanel">
			<label>选择主题</label>
			<select id="stylelist" onchange="changeMapStyle(this.value)"></select>
		</div>
	</div>
	<div id="group" onMouseOver="leftshow('group')" onMouseOut="lefthitten('group')">
		<div class="header">
        	<span class="headertitle">群聊天</span>
        	<img src="img/close.png" class="closeimg" onclick="lefthitten('group')">
        </div>
        <div class="panel" id="groupchatarea">
        
        </div>
        <div class="input">
        	<form>
            	<textarea class="chatedit" id="groupchatedit"></textarea>
                <% if(id.equals("null")) {
                %>
                <input type="button" value="发送" class="submit" id="groupsubmit" onclick="alert('您尚未登录，请点击菜单登录')" />
                <%}
                else{
                %>
                <input type="button" value="发送" class="submit" id="groupsubmit" onclick="sendRequest('groupchat','groupchatedit','groupchatarea')"/>
                <%} %>
            </form>
            <form>
            	<% 	
            		UserData user=new UserData();
        			user.getMember(id);
        			user.next();
        			String name=user.getName(); 
        			user.close();
            	%>
            	<input id="username" style="display:hitten;" value=<% out.print("'"+name+"'"); %>/>
            </form>
        </div>
	</div>
	<div id="personlist" onMouseOver="leftshow('personlist')" onMouseOut="lefthitten('personlist')">
		<div class="header">
        	<span id="headertitle" class="headertitle"></span>
        	<img src="img/close.png" class="closeimg" onclick="lefthitten('personlist')">
        </div>
        <div id="userlist" class="userlist">
        </div>
	</div> 
</body>
</html>
<script type="text/javascript" src="http://developer.baidu.com/map/custom/stylelist.js"></script>
<script type="text/javascript">
	var sel = document.getElementById('stylelist');
	for(var key in mapstyles){
		var style = mapstyles[key];
		var item = new  Option(style.title,key);
		sel.options.add(item);
	}
	var map = new BMap.Map("allmap");
	window.map = map;
	var point=new BMap.Point(117.1344028,36.6664415);
	function addMarker(point,label){
		var marker= new BMap.Marker(point);
		map.addOverlay(marker);	
		var templabel=new BMap.Label(label,{offset:new BMap.Size(20,-10)});
		marker.setLabel(templabel);
	}
	function deletePoint(){
		var allOverlay=map.getOverlays();
		for(var i=0;i<allOverlay.length;i++){
			map.removeOverlay(allOverlay[i]);
		}
	}
	function getLocation()
	{
		var oList=document.getElementById('menuelist');
		oList.style.display='none';
		oList.value=1;
	if (navigator.geolocation)
	  {
	  navigator.geolocation.getCurrentPosition(showPosition);
	  }
	else{window.alert("你的计算机不支持GPS定位");}
	}
	function showPosition(position)
	{	deletePoint();
		point = new BMap.Point(position.coords.longitude+0.01265,position.coords.latitude+0.00645);
		map.centerAndZoom(point, 14);
		sendLocation("location",position);
	}
	getLocation();
	map.addControl(new BMap.NavigationControl());           
	map.addControl(new BMap.ScaleControl());                 
	map.addControl(new BMap.OverviewMapControl());           
	map.enableScrollWheelZoom();                       
	map.addControl(new BMap.MapTypeControl());        
	map.disable3DBuilding();
	map.centerAndZoom(point, 14); 
	
	changeMapStyle('midnight')
	sel.value = 'midnight';

	function changeMapStyle(style){
		map.setMapStyle({style:style});
		$('#desc').html(mapstyles[style].desc);
	}
	
</script>
