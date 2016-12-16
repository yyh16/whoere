	    function validateEmpty(elementName){
		var objElement=document.getElementById(elementName);
		var msgElement=document.getElementById(elementName+"Msg");
		var msgDiv=document.getElementById("form-"+elementName);
		if(objElement.value!=""){
		    msgDiv.style.border="1px solid #0f0";
		    //objElement.className="right";		    
		    msgElement.innerHTML="<font color='green' style='font-family: '微软雅黑'; font-size: 15px;'>内容输入正确</font>";
		    return true;
		}else{
		    msgDiv.style.border="1px solid #f00"
		    //objElement.className="wrong";
		    msgElement.innerHTML="<font color='red' style='font-family: '微软雅黑'; font-size: 15px;'>内容不能为空</font>";
		    return false;
		}
	    }

	    function validateRepeat(srcName,desName){//确定内容是否相同
		var srcElement=document.getElementById(srcName);
		var desElement=document.getElementById(desName);
		var msgElement=document.getElementById(desName+"Msg");
		var msgDiv=document.getElementById("form-"+desName);
		if(srcElement.value==desElement.value){
		    msgDiv.style.border="1px solid #0f0";
		    //desElement.className="right";		    
		    msgElement.innerHTML="<font color='green' style='font-family: '微软雅黑'; font-size: 15px;'>输入内容正确</font>";
		    return true;
		}else{
		    msgDiv.style.border="1px solid #f00";
		    //desElement.className="wrong";
		    msgElement.innerHTML="<font color='red' style='font-family: '微软雅黑'; font-size: 15px;'>两次输入不相同</font>";
		    return false;
		}
	    }		

	   function validateRegex(eleName,regex){
		var obj=document.getElementById(eleName);
		var msg=document.getElementById(eleName+"Msg");
		var msgDiv=document.getElementById("form-"+eleName);
		if(regex.test(obj.value)){
		    msgDiv.style.border="1px solid #0f0";
		    //obj.className="right";
		    if(msg!=null){
			msg.innerHTML="<font color='green' style='font-family: '微软雅黑'; font-size: 15px;'>内容输入正确！</font>";
			}
		    return true;
		}else{
		    msgDiv.style.border="1px solid #f00";
		   // obj.className="wrong";
		    if(msg!=null){
			msg.innerHTML="<font color='red' style='font-family: '微软雅黑'; font-size: 15px;'>内容输入格式错误！</font>";
		}
		return false;
	    }
	    }
	    function validateRegName(){
		return validateEmpty("regName");
	    }

	    function validatePwd(){
		return validateEmpty("pwd");
	    }

	    function validateConf(){
		if(validateEmpty("pwdRepeat")){
		    return validateRepeat("pwd","pwdRepeat");
		}else{
		    return false;
		}
	    }
	    function validatePhone(){
		return validateEmpty("phone")&&
		       validateRegex("phone",/^1\d{10}$/);
	    }
	    
	    function validateEmail(){
		return validateEmpty("email")&& 
		       validateRegex("email",/^\w+@\w+\.\w+$/);
	    }
	    
	    function validate(){
		return validateRegName()&&
			validatePwd()&&
			validateConf()&&
			validatePhone()&&
			validateEmail();
	    }
	 
	   
