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
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 border-bottom">
                <h1 class="h2">이동수단</h1>
            </div>
            <div class="starter-template container">
                <div class="order-md-1">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mb-2">
                        <h3 class="mb-0 mt-2">상세</h3>
                        <div class="btn-group mr-2" id="btn-group-1">
                            <button class="btn btn-outline-primary" type="button" id="fnDeviceUpdate">수정</button>
                            <button class="btn btn-outline-secondary" type="button" id="fnDeviceList">목록</button>
                        </div>
                        <div class="btn-group mr-2" id="btn-group-2">
                            <button class="btn btn-outline-primary" type="button" id="fnDeviceUpdateSubmit">완료</button>
                            <button class="btn btn-outline-secondary" type="button" id="fnDeviceUpdateCancel">취소</button>
                        </div>
                    </div>
                    <form id="fmDeviceUpdate" class="needs-validation" novalidate>
                    <!-- 히든 요소 추가 -->
                    <input type="hidden" id="vehicleId" name="vehicleId" value="${vehicleId}" required>
                    <!-- company name: 추후 로그인 사용자의 소속 회사정보로 회사 id가 자동 입력되고, 회사명만 보이도록 변경. 이후 비활성화 상태 -->
                    <div class="mb-3">
                        <label for="cmpnId">회사명</label>
                        <select class="custom-select d-block w-100" id="cmpnId" required>
                        </select>
                        <div class="invalid-feedback">
                                Please enter your company name.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="vehicleTp">이동수단</label>
                        <select class="custom-select d-block w-100" id="vehicleTp" required>
                        </select>
                        <div class="invalid-feedback">
                                Please enter your vehicle type.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="vehicleNum">이동수단 식별번호</label>
                        <input type="text" class="form-control" id="vehicleNum" name="vehicleNum" placeholder="50보4304" required>
                        <div class="invalid-feedback">
                                Please enter your vehicle number.
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </main>
        <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        <script src="/js/common/form-validation.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                fnDetail();
                fnUpdateInactive();

                $("#btn-group-2").hide();

                // 목록으로 돌아가기
                $("#fnDeviceList").click(function() {
                    var url = "/view/vehicle/list";
                    $(location).attr('href',url);
                });
                // 수정하기
                $("#fnDeviceUpdateSubmit").click(function() {
                    var fmDeviceUpdate = $("#fmDeviceUpdate");
                    if(fmDeviceUpdate.isValid()) {
                        // 보내는 데이터
                        var sendData = {
                            vehicleId: $("#vehicleId").val(),
                            cmpnId: $("#cmpnId").val(),
                            vehicleTp: $("#vehicleTp").val(),
                            vehicleNum: $("#vehicleNum").val()
                        }
                        $.ajax({
                            url : '/data/vehicle/update',
                            type : 'POST',
                            contentType: 'application/json',
                            data : JSON.stringify(sendData),
                            success : function(data) {
                                var url = "/view/vehicle/list";
                                $(location).attr('href',url);
                            },
                            error : function(xhr, status) {
                                alert(xhr + " : " + status);
                            }
                        });
                    } else {
                        fmDeviceUpdate.addClass('was-validated');
                    }
                });
                // 수정하기(비활성화)
                $("#fnDeviceUpdateCancel").click(function() {
                    $("#btn-group-1").show();
                    $("#btn-group-2").hide();
                    fnDetail();
                    fnUpdateInactive();
                });
                // 수정하기(활성화)
                $("#fnDeviceUpdate").click(function() {
                    fnUpdateActive();
                    $("#btn-group-1").hide();
                    $("#btn-group-2").show();
                    $("#cmpnId").focus();
                });
            });
            // 상세회면 비활성화
            function fnUpdateInactive() {
                $("#cmpnId").attr("disabled",true);
                $("#vehicleTp").attr("disabled",true);
                $("#vehicleNum").attr("disabled",true);
            };
            // 상세화면 활성화
            function fnUpdateActive() {
                // 디바이스의 회사간 변경은 없다. 신규로 등록해야 함.
                // $("#cmpnId").removeAttr("disabled");
                $("#vehicleTp").removeAttr("disabled");
                $("#vehicleNum").removeAttr("disabled");
            };

            function fnDetail() {
                var sendData = {
                    vehicleId: "${vehicleId}"
                };
                $.ajax({
                    url : '/data/vehicle/detail',
                    type : 'POST',
                    contentType: 'application/json',
                    data : JSON.stringify(sendData),
                    success : function(data) {
                        fnCmpnList(data.cmpnId);
                        fnVehicleTpList(data.vehicleTp);
                        $("#vehicleNum").val(data.vehicleNum);
                    },
                    error : function(xhr, status) {
                        alert(xhr + " : " + status);
                    }
                });
            }

            // 로그인 사용자 회사명 조회
            function fnCmpnList(cmpnId) {
                $.ajax({
                    url : '/data/common/cmpn/' + cmpnId,
                    type : 'GET',
                    contentType: 'application/json',
                    success : function(element) {
                        $("select#cmpnId").append(
                            '<option value="' + element.cmpnId + '">' + element.cmpnNm + '</option>'
                        );
                    },
                    error : function(xhr, status) {
                        alert(xhr + " : " + status);
                    }
                });
            }

            // 디바이스 제조회사 콤보박스 조회
            function fnVehicleTpList(vehicleTp) {
                $.ajax({
                    url : '/data/code/vehicle-tp-list',
                    type : 'GET',
                    contentType: 'application/json',
                    success : function(data) {
                        data.forEach(element => {
                                if(element.cd == vehicleTp) {
                                     $("select#vehicleTp").append(
                                        '<option value="' + element.cd + '" selected=true>' + element.cdDc + '</option>'
                                     );
                                } else {
                                    $("select#vehicleTp").append(
                                        '<option value="' + element.cd + '">' + element.cdDc + '</option>'
                                    );
                                }
                        });
                    },
                    error : function(xhr, status) {
                        alert(xhr + " : " + status);
                    }
                });
            }
        </script>
    </body>
</html>
