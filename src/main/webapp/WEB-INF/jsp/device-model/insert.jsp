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
                        <h3 class="mb-0 mt-2">등록</h3>
                        <div class="btn-group mr-2">
                            <button class="btn btn-outline-secondary" type="button" id="btnList">목록</button>
                        </div>
                    </div>
                    <form id="fmDeviceInsert" class="needs-validation" novalidate>
                        <div class="mb-3">
                            <label for="mfgComId">디바이스 제조사</label>
                            <select class="custom-select d-block w-100" id="mfgComId" required>
                                <option value="">선택</option>
                            </select>
                            <div class="invalid-feedback">
                                    Please select a manufacturer company name.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="dvcMdlNm">디바이스 모델명</label>
                            <input type="text" class="form-control" id="dvcMdlNm" name="dvcMdlNm" placeholder="device model 123" required>
                            <div class="invalid-feedback">
                                    Please enter your device model name.
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

                var mfgComId = "${mfgComId}";

                // 콤보박스 초기화
                initComboBox(mfgComId);

                $("#fmDeviceInsertSubmit").click(function() {
                    checkFormValidate($("#fmDeviceInsert"), function() {
                        fnCommPostCall("device_model_data_insert", {
                            dvcMdlNm: $("#dvcMdlNm").val(),
                            mfgComId: $("#mfgComId").val()
                        }, function(data) {
                            locationUrl("device_model_view_list");
                        });
                    });
                });
                $("#btnList").click(function() {
                    locationUrl("device_model_view_list");
                });
            });

            function initComboBox(mfgComId) {

                // 디바이스 제조회사 콤보박스
                fnCommPostCall("common_data_mfg_list", {}, function(data) {
                    data.forEach(element => {
                        $("select#mfgComId").append(
                            '<option value="' + element.mfgComId + '">' + element.mfgComNm + '</option>'
                        );
                    });
                    if(mfgComId != "") {
                        $("select#mfgComId").val(mfgComId);
                        $("select#mfgComId").attr("disabled",true);
                    }
                });
            }
        </script>
    </body>
</html>
