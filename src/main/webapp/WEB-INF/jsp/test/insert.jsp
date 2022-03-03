<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <meta name="description" content="">
        <meta name="author" content="">

        <title>Starter Template for Bootstrap</title>

        <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
        <link href="/css/bootstrap/form-validation.css" rel="stylesheet">
    </head>
    <body style="overflow:scroll">
        <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">디바이스 등록</h1>
                <!-- <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
                    </div>
                    <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <span data-feather="calendar"></span>
                                This week
                    </button>
                </div> -->
            </div>
            <div class="starter-template container">
                <!-- <div class="py-5 text-center">
                    <h2>Checkout form</h2>
                    <p class="lead">Below is an example form built entirely with Bootstrap’s form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>
                </div> -->
                <div class="order-md-1">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mb-2">
                        <h4>Device 등록</h4>
                        <div class="btn-toolbar mb-2 mb-md-0">
                            <div class="btn-group mr-2">
                                <button class="btn btn-block btn-sm btn-outline-primary" type="button" id="btnList">목 록</button>
                            </div>
                        </div>
                    </div>
                    <!-- <div class="btn-group mr-2">
                        <button class="btn btn-outline-secondary" type="button" id="btnList">목 록</button>
                    </div> -->
                    <form id="fmDeviceInsert" class="needs-validation" novalidate>

                        <!-- 한줄에 2개 -->
                        <!-- <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">First name</label>
                                <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
                                <div class="invalid-feedback">
                                    Valid first name is required.
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Last name</label>
                                <input type="text" class="form-control" id="lastName" placeholder="" value="" required>
                                <div class="invalid-feedback">
                                        Valid last name is required.
                                </div>
                            </div>
                        </div> -->
                        <!-- <div class="mb-3">
                            <label for="username">Username</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">@</span>
                                </div>
                                <input type="text" class="form-control" id="username" placeholder="Username" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                        Your username is required.
                                </div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="email">Email <span class="text-muted">(Optional)</span></label>
                            <input type="email" class="form-control" id="email" placeholder="you@example.com">
                            <div class="invalid-feedback">
                                    Please enter a valid email address for shipping updates.
                            </div>
                        </div> -->
                        <!-- 한줄에 1개 -->
                        <!-- company name: 추후 로그인 사용자의 소속 회사정보로 회사 id가 자동 입력되고, 회사명만 보이도록 변경. 이후 비활성화 상태 -->
                        <div class="mb-3">
                            <label for="companyNm">Company Name</label>
                            <input type="text" class="form-control" id="companyNm" name="companyNm" placeholder="ruokit" required>
                            <div class="invalid-feedback">
                                    Please enter your company name.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="hndsetId">Device Phone Number</label>
                            <input type="text" class="form-control" id="hndsetId" name="hndsetId" placeholder="01085671470" required>
                            <div class="invalid-feedback">
                                    Please enter your device phone number.
                            </div>
                        </div>
                        <!-- device model name: 추후 고객사별 디바이스 검색을 통한 디바이스 모델id 입력으로 변경-->
                        <div class="mb-3">
                            <label for="deviceMdlNm">Device Model Name</label>
                            <input type="text" class="form-control" id="deviceMdlNm" name="deviceMdlNm" placeholder="device model 123" required>
                            <div class="invalid-feedback">
                                    Please enter your device model name.
                            </div>
                        </div>
                        <!-- <div class="mb-3">
                            <label for="address2">Address 2 <span class="text-muted">(Optional)</span></label>
                            <input type="text" class="form-control" id="address2" placeholder="Apartment or suite">
                        </div> -->
                        <!-- <div class="row">
                            <div class="col-md-5 mb-3">
                                <label for="country">Country</label>
                                <select class="custom-select d-block w-100" id="country" required>
                                    <option value="">Choose...</option>
                                    <option>United States</option>
                                </select>
                                <div class="invalid-feedback">
                                        Please select a valid country.
                                </div>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="state">State</label>
                                <select class="custom-select d-block w-100" id="state" required>
                                    <option value="">Choose...</option>
                                    <option>California</option>
                                </select>
                                <div class="invalid-feedback">
                                        Please provide a valid state.
                                </div>
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="zip">Zip</label>
                                <input type="text" class="form-control" id="zip" placeholder="" required>
                                <div class="invalid-feedback">
                                        Zip code required.
                                </div>
                            </div>
                        </div>
                        <hr class="mb-4">
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="same-address">
                            <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="save-info">
                            <label class="custom-control-label" for="save-info">Save this information for next time</label>
                        </div>
                        <hr class="mb-4">
                        <h4 class="mb-3">Payment</h4>
                        <div class="d-block my-3">
                            <div class="custom-control custom-radio">
                                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required>
                                <label class="custom-control-label" for="credit">Credit card</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required>
                                <label class="custom-control-label" for="debit">Debit card</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                                <label class="custom-control-label" for="paypal">PayPal</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="cc-name">Name on card</label>
                                <input type="text" class="form-control" id="cc-name" placeholder="" required>
                                <small class="text-muted">Full name as displayed on card</small>
                                <div class="invalid-feedback">
                                        Name on card is required
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="cc-number">Credit card number</label>
                                <input type="text" class="form-control" id="cc-number" placeholder="" required>
                                <div class="invalid-feedback">
                                        Credit card number is required
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3 mb-3">
                                <label for="cc-expiration">Expiration</label>
                                <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                                <div class="invalid-feedback">
                                        Expiration date required
                                </div>
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="cc-cvv">CVV</label>
                                <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                                <div class="invalid-feedback">
                                        Security code required
                                </div>
                            </div>
                        </div> -->
                        <hr class="mb-3">
                        <button class="btn btn-primary btn-block" type="button" id="fmDeviceInsertSubmit">등 록 하 기</button>
                        </form>
                        <!-- <div style="text-align:right">
                            <div class="btn-group mr-2">
                                <button class="btn btn-lg btn-block btn-outline-primary" type="button" id="fmDeviceInsertSubmit">등 록</button>
                            </div>
                        </div> -->
                    </div>
                </div>
            </div>
            <!-- <footer class="my-5 pt-5 text-muted text-center text-small">
                <p class="mb-1">&copy; 2021 Ruokit.com</p>
                <ul class="list-inline">
                    <li class="list-inline-item"><a href="#">Privacy</a></li>
                    <li class="list-inline-item"><a href="#">Terms</a></li>
                    <li class="list-inline-item"><a href="#">Support</a></li>
                </ul>
            </footer> -->
        </main>
        <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        <script src="/js/common/form-validation.js"></script>
        <script type="text/javascript">
            $("#fmDeviceInsertSubmit").click(function() {
                var fmDeviceInsert = $("#fmDeviceInsert");
                if(fmDeviceInsert.isValid()) {
                    // 보내는 데이터
                    var sendData = {
                        companyNm: $("#companyNm").val(),
                        hndsetId: $("#hndsetId").val(),
                        deviceMdlNm: $("#deviceMdlNm").val()
                    }
                    $.ajax({
                        url : '/device-model/data/insert',
                        type : 'POST',
                        contentType: 'application/json',
                        data : JSON.stringify(sendData),
                        success : function(data) {
                            var url = "/device-model/view/read";
                            $(location).attr('href',url);
                        },
                        error : function(xhr, status) {
                            alert(xhr + " : " + status);
                        }
                    });
                } else {
                    fmDeviceInsert.addClass('was-validated');
                }
            });
            $("#btnList").click(function() {
                if(confirm("등록을 취소하시겠습니까?")) {
                    var url = "/device-model/view/read";
                    $(location).attr('href',url);
                };
            });
        </script>
    </body>
</html>
