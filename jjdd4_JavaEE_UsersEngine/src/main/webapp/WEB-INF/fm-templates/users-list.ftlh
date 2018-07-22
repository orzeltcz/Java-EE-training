<!DOCTYPE html>
<html xmlns="http://java.sun.com/jsf/composite">
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
    </#if>
    <div style="color: ${color}">
        ID: ${user.id}<br/>
        Imie: ${user.name}<br/>
        Login: ${user.login}<br/>
        Wiek: ${user.age}<br/>
        Plec: ${user.gender}<br/>
        <#if user.password??>
            Password: ${user.password}<br/>
        </#if>
        Link: <a href="/user?id=${user.id}">Edit</a>
       <#if user.imageUrl??>
                <li>
                    <img width="200" src="${user.imageUrl}"/>
                </li>
       </#if>
    <#-- to jest tajemniczy komentarz-->
    </div>
    <#sep>--------------------------------------</#sep><br/>
    </#items>
<ul>
</#list>
<#include "footer.ftlh">
</body>
</html>