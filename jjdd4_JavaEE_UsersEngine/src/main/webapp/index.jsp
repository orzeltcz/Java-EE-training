<html>
<body>
<h2>Hello World!</h2>
<form action="/welcome-user" method="get">
    <input type="text" name="imie" placeholder="Podaj imie">
    <input type="text" name="salary" placeholder="Podaj salary">

    <input type="submit" value="Wyslij">
</form>
<form action="/finduser" method="get">
    <input type="text" name="id" placeholder="Podaj ID">
    <input type="submit" value="Wyslij">
</form>
<h1>Add user from add-user.html</h1>
<form method="post" action="/add-user" enctype="multipart/form-data">
    ID: <input type="text" name="id"/><br/><br/>
    Name: <input type="text" name="name"/><br/><br/>
    Login: <input type="text" name="login"/><br/><br/>
    Password: <input type="password" name="password"/><br/><br/>
    Age: <input type="text" name="age"/><br/><br/>
    Image: <input type="file" name="image">
    <input type="submit" name="save" value="save"/><br/><br/>
</form>
</body>
</html>
