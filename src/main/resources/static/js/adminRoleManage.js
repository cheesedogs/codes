$(document).ready(function() {
    getUsers();

    function getUsers() {
        getRequest(
            '/admin/getAllUser',
            function (res) {
                var users = res.content;
                showUsers(users);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function showUsers(users) {
        for (let user of users) {
            var table = document.getElementById("table");
            var tr = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            var td4 = document.createElement("td");
            var td5 = document.createElement("td");
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            table.appendChild(tr);
            td1.innerHTML = user.id;
            td2.innerHTML = user.username;
            td3.innerHTML = user.password;
            td4.innerHTML = user.identity;
            td5.className = "td-manage";
            td5.innerHTML = "<a title='编辑' onclick=\"xadmin.open('用户修改','edit',1200,450)\"'>" +
                                "<i class='layui-icon'>&#xe642;</i>" +
                            "</a>" +
                            "<a title='删除' onclick=\"member_del(this,'要删除的id')\"'>" +
                                "<i class='layui-icon'>&#xe640;</i>" +
                            "</a>";
        }
    }
});