<html>
<body>
<h2>Hello World!</h2>
<h3>${method}</h3>
<form action="/user" method="${method}" enctype="multipart/form-data">
    <#if method=="post">
    Name: <input type="text" name="name"/><br/><br/>
    Login: <input type="text" name="login"/><br/><br/>
    Password: <input type="password" name="password"/><br/><br/>
    Age: <input type="text" name="age"/><br/><br/>
    Image: <input type="file" name="image">
    <#else>
        ${User.name}<br/>
    Name: <input type="text" name="name"/><br/><br/>
        ${User.login}<br/>
    Login: <input type="text" name="login"/><br/><br/>
    Password: <input type="password" name="password"/><br/><br/>
        ${User.age}<br/>
    Age: <input type="text" name="age"/><br/><br/>
    </#if>
    <input type="submit" value="Wyslij">
</form>
</body>
</html>
