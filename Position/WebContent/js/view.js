function getStyle(obj, name)
{
	if(obj.currentStyle)
	{
		return obj.currentStyle[name];
	}
	else
	{
		return getComputedStyle(obj, false)[name];
	}
}

function startMove(obj, json, fnEnd)
{	
	clearInterval(obj.timer);
	obj.timer=setInterval(function (){
		var bStop=true;		//假设：所有值都已经到了
		
		for(var attr in json)
		{
			var cur=0;
			
			if(attr=='opacity')
			{
				cur=Math.round(parseFloat(getStyle(obj, attr))*100);
			}
			else
			{
				cur=parseInt(getStyle(obj, attr));
			}
			
			var speed=(json[attr]-cur)/6;
			speed=speed>0?Math.ceil(speed):Math.floor(speed);
			
			if(cur!=json[attr])
				bStop=false;
			
			if(attr=='opacity')
			{
				obj.style.filter='alpha(opacity:'+(cur+speed)+')';
				obj.style.opacity=(cur+speed)/100;
			}
			else
			{
				obj.style[attr]=cur+speed+'px';
			}
		}
		
		if(bStop)
		{
			clearInterval(obj.timer);
						
			if(fnEnd)fnEnd();
		}
	}, 30);
}

function leftshow(element){
	var oList=document.getElementById('menuelist');
	oList.style.display='none';
	oList.value=1;
	var oDiv=document.getElementById(element);
	oDiv.style.display='block';
		startMove(oDiv,{left:0});
}
function showmenuepanel(){
	var oList=document.getElementById('menuelist');
	if(oList.value==0){
		oList.style.display='none';
		oList.value=1;
	}else{
		oList.style.display='block';
		oList.value=0;
	}
}
var grouplist=0;
var personallist=0;
function appendchat(text,to){
	var index=0;
	var now=0;
	var priority='';
	if(to=='groupchatarea'){
		grouplist++;
		index=(grouplist)%5;
		now=grouplist;
		priority='groupindex';
	}else{		
		personallist++;
		index=(personallist)%5;
		now=personallist;
		priority='personalindex';
		
	}
	var oTo=document.getElementById(to);
	if(now<5){
		var node=document.createElement("div");
		var textnode=document.createTextNode(text); 
		node.id=priority+now;
		oTo.appendChild(node);
	}else{
		for(var i=1;i<4;i++){
			var thisNode=document.getElementById(priority+i);
			var nextNode=document.getElementById(priority+(i+1));
			thisNode.innerHTML=nextNode.innerHTML;
		}
		index=4;
	}
	var add=document.getElementById(priority+index);
	add.innerHTML=text;
}

var actionArea='';
XMLHttpReq=null;
function connectioncreacte(){
    if(window.XMLHttpRequest){
        XMLHttpReq = new XMLHttpRequest();
     }else if(window.ActiveXObject){
        try{
            XMLHttpReq = new ActiveXObject("MSXML2.XMLHTTP");
        }catch(e){
            try{
               XMLHttpReq = new ActiveXObject("Mircsoft.XMLHTTP");
            }catch(e1){}
        }
     }
}
function sendRequest(url,edit,area){
	connectioncreacte();
    var oEdit=document.getElementById(edit);
	var senttext=oEdit.value;
	oEdit.value='';
    XMLHttpReq.open("POST",url+"?text="+senttext,true);
    XMLHttpReq.onreadystatechange = processResponse;
    XMLHttpReq.send(null);
	if(area=='groupchatarea'){
		actionArea='groupchatarea';
	}else{
		actionArea='personalchatarea';
	}
}
function processResponse(){
	var text=XMLHttpReq.responseText;
	var status=XMLHttpReq.readyState;
    if(status == 4){
       if(XMLHttpReq.status == 200){
           appendchat(text,actionArea);
       }else{
           window.alert("对不起无法发送消息");
       }
    }  
}
function sendLocation(url,location){
	connectioncreacte();
    if(window.XMLHttpRequest){
        XMLHttpReq = new XMLHttpRequest();
     }else if(window.ActiveXObject){
        try{
            XMLHttpReq = new ActiveXObject("MSXML2.XMLHTTP");
        }catch(e){
            try{
               XMLHttpReq = new ActiveXObject("Mircsoft.XMLHTTP");
            }catch(e1){}
        }
     }
    XMLHttpReq.open("POST",url+"?longitude="+(location.coords.longitude+0.01265)+"&latitude="+(location.coords.latitude+0.00645),true);
    XMLHttpReq.onreadystatechange = showallposition;
    XMLHttpReq.send(null);
    
}
function showallposition(){
	var text=XMLHttpReq.responseText;
	var status=XMLHttpReq.readyState;
    if(status == 4){
       if(XMLHttpReq.status == 200){
         var number=0;
		 var i=0;
		 var j=0;
		 var name='';
		 var y=0;
		 var x=0;
		 while(i<text.length){
			 if(text.charAt(i)=='&'){
				if(number==0){
					name=text.substring(j,i);
					number=1;	
				}else if(number==1){
					y=parseFloat(text.substring(j,i));
					number=2;
				}else if(number==2){
					x=parseFloat(text.substring(j,i));
					point = new BMap.Point(y,x);
					addMarker(point,name);
					number=0;
				}
				
				j=i+1;
			}
			i=i+1;
		 }
    	   
    	   
       }else{
           window.alert("对不起后台处理定位出现错误");
       }
    }
}
function requestUser(url){
	connectioncreacte();
    if(window.XMLHttpRequest){
        XMLHttpReq = new XMLHttpRequest();
     }else if(window.ActiveXObject){
        try{
            XMLHttpReq = new ActiveXObject("MSXML2.XMLHTTP");
        }catch(e){
            try{
               XMLHttpReq = new ActiveXObject("Mircsoft.XMLHTTP");
            }catch(e1){}
        }
     }
    XMLHttpReq.open("POST",url+"?",true);
    XMLHttpReq.onreadystatechange = showUser;
    XMLHttpReq.send(null);    
    leftshow("personlist");  
}
function showUser(){
	var text=XMLHttpReq.responseText;
	var status=XMLHttpReq.readyState;
	var oTitle=document.getElementById("headertitle");
	oTitle.innerHTML="在线用户列表";
    if(status == 4){
       if(XMLHttpReq.status == 200){
    	   var oDiv=document.getElementById('userlist');
    	   oDiv.innerHTML=text;
       }
    }
}
function showSuggest(){
	leftshow("personlist");
	var oTitle=document.getElementById("headertitle");
	oTitle.innerHTML="使用手册";
    var oDiv=document.getElementById('userlist');
    oDiv.innerHTML="<span stlye='margin:10px;font-size:16px;'>请不要直接使用qq发送的链接打开，没有位置信息；"+
    "复制链接到浏览器中打开，允许获得位置即可分享位置。可以使用手机和电脑" +
    "同时登录，并且位置以手机为准，建议使用手机登录；目前实现的功能有群聊天：发送一条消息即可获得附近人的消息；" +
    "用户列表：查看当前在线并分享位置的用户；重定位：当前不能实时更新用户位置信息和所有人的位置，可以使用重定位"+
    "更新自己的位置和别人的位置；退出登录：退出聊天页面，如果你需要更改用户可以使用。如果"+
    "在使用过程中遇到问题，请联系1015310072@qq.com.谢谢使用！</span>";
}

