<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>[[${pageTitle}]] | Multi Programing Course</title>
    <link rel="stylesheet" type="text/css" th:href="@{/webjars/bootstrap/5.3.0/css/bootstrap.min.css}">
</head>
<body>
    <section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
            <div class="row justify-content-center align-items-center h-100">
                <div class="col-12">
                    <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                        <div class="card-body p-4 p-md-5">
                            <div class="row justify-content-center">
                                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-4">[[${pageTitle}]]</h3>
                                    <form th:action="@{/user/save}" method="POST" th:object="${user}">
                                        <input type="hidden" th:field="*{id}"/>
                                        <div class="row">
                                            <div class="col-md-6 mb-2">
                                                <div class="form-outline">
                                                    <label class="form-label" for="firstName">First Name</label>
                                                    <input type="text" id="firstName" th:field="*{firstName}" required minlength="2" maxlength="45" class="form-control form-control" />
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-2">
                                                <div class="form-outline">
                                                    <label class="form-label" for="lastName">Last Name</label>
                                                    <input type="text" id="lastName" th:field="*{lastName}" required minlength="2" maxlength="45" class="form-control form-control" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-2 d-flex align-items-center">
                                                <div class="form-outline datepicker w-100">
                                                    <label for="enabled" class="form-label">Enabled</label>
                                                    <input class="form-check-input me-2 form-control" type="checkbox" th:field="*{enabled}" id="enabled" />
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-2">
                                                <h6 class="mb-2 pb-1">Gender: </h6>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" th:field="*{gender}" type="radio" id="femaleGender" value="female"
                                                        checked />
                                                    <label class="form-check-label" for="femaleGender">Female</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" id="maleGender" th:field="*{gender}" value="male" />
                                                    <label class="form-check-label" for="maleGender">Male</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="radio" th:field="*{gender}" id="otherGender" value="other" />
                                                    <label class="form-check-label" for="otherGender">Other</label>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12 mb-2 pb-2">
                                                <div class="form-outline">
                                                    <label class="form-label" for="emailAddress">Email</label>
                                                    <input type="email" th:field="*{email}" required minlength="8" maxlength="45" id="emailAddress" class="form-control form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12 mb-2 pb-2">
                                                <div class="form-outline">
                                                    <label class="form-label" for="password">Password</label>
                                                    <input type="password" id="password" th:field="*{password}"  class="form-control" required minlength="5" maxlength="15" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12 mb-2 pb-2">
                                                <div class="form-outline">
                                                    <label class="form-label" for="confirmPassword">Confirm Password</label>
                                                    <input type="password" name="confirmPassword" required minlength="5" maxlength="45" id="confirmPassword" class="form-control form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mt-3 pt-2 w-100">
                                            <button class="btn-outline-primary btn w-100" type="submit">Register</button>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp" class="img-fluid" alt="Sample image">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>