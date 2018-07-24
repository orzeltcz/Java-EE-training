<html>
<head>

</head>
<body>
<#if errorMessage??>
    <span style="color:red;">${errorMessage}</span>
</#if>
<#if user??>
<h1>Witaj: ${user.name}<br/>
Login: ${user.login}<br/>
Wiek: ${user.age}<br/>
Plec: ${user.gender}<br/>
Puls: ${pulse}</h1>
</#if>
</body>
</html>