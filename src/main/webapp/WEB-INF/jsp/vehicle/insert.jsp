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
                        <h3 class="mb-0 mt-2">등록</h3>
                        <div class="btn-group mr-2">
                            <button class="btn btn-outline-secondary" type="button" id="btnContinue">계속</button>
                            <button class="btn btn-outline-secondary" type="button" id="btnList">목록</button>
                        </div>
                    </div>
                    <form id="fmDeviceInsert" class="needs-validation" novalidate>
                        <!-- 히든 요소 추가 -->
                        <input type="hidden" id="isContinue" value="false">
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
                                <option value="">선택</option>
                            </select>
                            <div class="invalid-feedback">
                                    Please select a vehicle type name.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="vehicleNum">이동수단 식별번호</label>
                            <input type="text" class="form-control" id="vehicleNum" name="vehicleNum" placeholder="50보4304" required>
                            <div class="invalid-feedback">
                                    Please enter your vehicle number.
                            </div>
                        </div>
                        <hr class="mb-3">
                        <button class="btn btn-primary btn-block" type="button" id="fmDeviceInsertSubmit">등 록 하 기</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        <script src="/js/common/form-validation.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                // 콤보박스 초기화
                initComboBox();
                $("#cmpnId").attr("disabled",true);

                // 등록 버튼
                $("#fmDeviceInsertSubmit").click(function() {
                    checkFormValidate($("#fmDeviceInsert"), function() {
                        fnCommPostCall("vehicle_data_insert", {
                            cmpnId: $("#cmpnId").val(),
                            vehicleTp: $("#vehicleTp").val(),
                            vehicleNum: $("#vehicleNum").val()
                        }, function(data) {
                            if($("#isContinue").val() == "true") {
                                alert("등록이 완료되었습니다.");
                                var moveUrl = function() {
                                    return findUrl("vehicle_view_insert") + "?isContinue=" + $("#isContinue").val();
                                }
                                locationUrlHandler(moveUrl);
                            } else {
                                locationUrl("vehicle_view_list");
                            }

                        });
                    });
                });

                // 계속 등록
                $("#btnContinue").click(function() {
                    var isContinue = $("#isContinue");
                    if(isContinue.val() == "false") {
                        isContinue.val("true")
                        changeAttrClass(this, "btn-outline-secondary", "btn-outline-primary");
                    } else if(isContinue.val() == "true") {
                        isContinue.val("false")
                        changeAttrClass(this, "btn-outline-primary", "btn-outline-secondary");
                    }
                });

                // 목록 버튼
                $("#btnList").click(function() {
                    locationUrl("vehicle_view_list");
                });

                // 계속 등록진행 중
                if("${isContinue}" == "true") {
                    $("#btnContinue").trigger('click');
                }
            });

            function initComboBox() {
                var cmpnId = "${cmpnId}";
                if(cmpnId != "") {
                    fnCmpnList(cmpnId);
                }
                fnVehicleTpList();
            }

            // 디바이스 제조회사 콤보박스 조회
            function fnVehicleTpList() {
                fnCommGetCall(function() {
                    return findUrl("code_vehicle_type_list");
                }, function(data) {
                    data.forEach(element => {
                        $("select#vehicleTp").append(
                            '<option value="' + element.cd + '">' + element.cdDc + '</option>'
                        );
                    });
                });
            }

            // 제조회사 별 디바이스 모델 콤보박스 조회
            function fnDeviceModelList(mfgComId) {

                var selectBox = $("select#dvcMdlId");
                initSelectBox(selectBox);

                fnCommPostCall("device_model_data_combo", {
                    mfgComId: mfgComId
                }, function(data) {
                    data.forEach(element => {
                        $(selectBox).append(
                            '<option value="' + element.dvcMdlId + '">' + element.dvcMdlNm + '</option>'
                        );
                    });
                });
            }

            // 로그인 사용자 회사명 조회
            function fnCmpnList(cmpnId) {
                fnCommGetCall(function() {
                    return findUrl("common_data_cmpn") + "/" + cmpnId;
                }, function(element) {
                    $("select#cmpnId").append(
                        '<option value="' + element.cmpnId + '">' + element.cmpnNm + '</option>'
                    );
                });
            }
        </script>


    </body>
</html>
