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
                <h1 class="h2">디바이스 모델</h1>
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
                    <input type="hidden" id="dvcMdlId" name="dvcMdlId" value="${dvcMdlId}" required>
                    <!-- device model name: 추후 고객사별 디바이스 검색을 통한 디바이스 모델id 입력으로 변경-->
                    <div class="mb-3">
                        <label for="mfgComId">디바이스 제조사</label>
                        <select class="custom-select d-block w-100" id="mfgComId" required>
                            <option value="">선택</option>
                        </select>
                        <div class="invalid-feedback">
                                Please select a manufacturer company name.
                        </div>
                    </div>
                    <!-- company name: 추후 로그인 사용자의 소속 회사정보로 회사 id가 자동 입력되고, 회사명만 보이도록 변경. 이후 비활성화 상태 -->
                    <div class="mb-3">
                        <label for="dvcMdlNm">디바이스 모델명</label>
                        <input type="text" class="form-control" id="dvcMdlNm" name="dvcMdlNm" placeholder="device model 123" required>
                        <div class="invalid-feedback">
                                Please enter your device model name.
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
                // 콤보박스 초기화
                initComboBox(fnDetail);
                fnUpdateInactive();
                $("#btn-group-2").hide();

                // 목록으로 돌아가기
                $("#fnDeviceList").click(function() {
                    locationUrl("device_model_view_list");
                });
                // 수정하기
                $("#fnDeviceUpdateSubmit").click(function() {
                    checkFormValidate($("#fmDeviceUpdate"), function() {
                        fnCommPostCall("device_model_data_update", {
                            dvcMdlId: $("#dvcMdlId").val(),
                            dvcMdlNm: $("#dvcMdlNm").val(),
                            mfgComId: $("#mfgComId").val()
                        }, function(data) {
                            locationUrl("device_model_view_list");
                        });
                    });
                });
                // 수정하기(비활성화)
                $("#fnDeviceUpdateCancel").click(function() {
                    fnUpdateInactive();
                    $("#btn-group-1").show();
                    $("#btn-group-2").hide();
                    // 리로드
                    fnDetail();
                });
                // 수정하기(활성화)
                $("#fnDeviceUpdate").click(function() {
                    fnUpdateActive();
                    $("#btn-group-1").hide();
                    $("#btn-group-2").show();
                });
            });
            // 상세회면 비활성화
            function fnUpdateInactive() {
                fnElemDisabledById(["dvcMdlNm", "mfgComId"]);
            };
            // 상세화면 활성화
            function fnUpdateActive() {
                fnElemUnDisabledById(["dvcMdlNm"]);
            };

            function fnDetail() {
                fnCommPostCall("device_model_data_detail", {
                    dvcMdlId: "${dvcMdlId}"
                }, function(data) {
                    $("#dvcMdlNm").val(data.dvcMdlNm);
                    $("#mfgComId").val(data.mfgComId).prop("selected", true);
                });
            }

            function initComboBox(callback) {
                fnCommPostCall("common_data_mfg_list", {}, function(data) {
                    data.forEach(element => {
                        $("select#mfgComId").append(
                            '<option value="' + element.mfgComId + '">' + element.mfgComNm + '</option>'
                        );
                    });
                    callback();
                });
            }
        </script>
    </body>
</html>
