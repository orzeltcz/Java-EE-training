<!DOCTYPE html>
<html>
<head>

</head>
<body>

<#list users>
<ul>
    <#items as user>
    <#if user.age lt 20>
    <#assign color="blue">
    <#elseif user.age gt 20 && user.age lt 40>
    <#assign color="green">
    <#elseif user.age gt 40>
    <#assign color="red">

        Imie: ${user.name}<br/>
        Login: ${user.login}<br/>
        Wiek: ${user.age}<br/>
        Plec: ${user.gender}<br/>
    <#-- to jest tajemniczy komentarz-->
    </#if>
    <#sep>--------------------------------------</#sep><br/>
    </#items>
<ul>
</#list>
</body>
</html>