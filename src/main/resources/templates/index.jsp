<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | Multi Programing Course</title>
    <link rel="stylesheet" type="text/css" th:href="@{/webjars/bootstrap/5.3.0/css/bootstrap.min.css}">
</head>

<body>
    <div class="container-fluid text-center mt-2">
        <div class="d-flex align-items-center justify-content-between">
            <h2>Hello, Bootstrap!</h2>
            <div>
                <a class="btn btn-primary" href="register">Register</a>
                <a class="btn btn-primary ms-2" href="login">Login</a>
            </div>
        </div>
        <div class="d-flex align-items-center justify-content-center mt-4 w-100">
            <div class="text-center mt-4 w-75">
                <h1>Manager Users</h1>
                <div th:if="${messages}" class="alert text-center alert-success">
                    [[${messages}]]
                </div>
                <div class="mt-4">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">First</th>
                                <th scope="col">Last</th>
                                <th scope="col">Email</th>
                                <th scope="col">Gender</th>
                                <th scope="col">Enabled</th>
                                <th scope="col">Handle</th>
                            </tr>
                        </thead>
                        <tbody>
                            <th:block th:each="user : ${userList}" <tr>
                                <th scope="row">[[${user.id}]]</th>
                                <td>[[${user.firstName}]]</td>
                                <td>[[${user.lastName}]]</td>
                                <td>[[${user.email}]]</td>
                                <td>[[${user.gender}]]</td>
                                <td>[[${user.enabled}]]</td>
                                <td>
                                    <a class="btn btn-outline-warning" th:href="@{'/user/edit/' + ${user.id}}">Edit</a>
                                    <a class="btn btn-outline-danger" th:href="@{'/user/delete/' + ${user.id}}">Delete</a>
                                </td>
                                </tr>
                            </th:block>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script src="/webjars/bootstrap/5.3.0/js/bootstrap.min.js"></script>
</body>

</html>