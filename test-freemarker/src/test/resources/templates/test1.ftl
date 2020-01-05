<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stus as stu>
        <tr>
            <td>${stu_index + 1}</td>
            <td <#if stu.name =='小明'>style="background:red;"</#if>>${stu.name}</td>
            <td>${stu.age}</td>
            <td >${stu.money}</td>
        </tr>
    </#list>

</table>

</br>
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>出生日期</td>
        <td>钱包</td>
        <td>最好的朋友</td>
        <td>朋友个数</td>
        <td>朋友列表</td>
    </tr>
    <#if stus??>
        <#list stus as stu>
            <tr>
                <td>${stu.name!''}</td>
                <td>${stu.age}</td>
                <td>${(stu.birthday?date)!''}</td>
                <td>${stu.money}</td>
                <td>${(stu.bestFriend.name)!''}</td>
                <td>${(stu.friends?size)!0}</td>
                <td>
                    <#if stu.friends??>
                        <#list stu.friends as firend>
                            ${firend.name!''}<br/>
                        </#list>
                    </#if>
                </td>
            </tr>
        </#list>
    </#if>

</table>
<br/>
<#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
<#assign data=text?eval />
开户行：${data.bank}  账号：${data.account}

</body>
</html>