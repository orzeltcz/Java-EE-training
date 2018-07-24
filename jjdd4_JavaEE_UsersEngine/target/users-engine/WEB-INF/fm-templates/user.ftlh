<html>
<body>
<h2>Hello World!</h2>
<h3>${method}</h3>
<form action="/user" method="post" enctype="multipart/form-data">
    <#if method=="post">
    Name: <input type="text" name="name"/><br/><br/>
    Login: <input type="text" name="login"/><br/><br/>
    Password: <input type="password" name="password"/><br/><br/>
    Age: <input type="text" name="age"/><br/><br/>
    Image: <input type="file" name="image">
    <#else>
    Name: <input type="text" value="${User.name}" name="name"/><br/><br/>
    Login: <input type="text" value="${User.login}" name="login"/><br/><br/>
    Password: <input type="password" name="password"/><br/><br/>
    Age: <input type="text" value="${User.age}" name="age"/><br/><br/>
    </#if>
    <input type="submit" value="Wyslij">
</form>
</body>
</html>
