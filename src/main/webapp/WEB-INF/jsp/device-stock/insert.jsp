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
        <style>
            .modal-open { padding-right: 0px !important; }
        </style>
        <link href="/plugins/jqgrid/4.4.3/css/ui.jqgrid.css" rel="stylesheet">
        <link href="/plugins/jquery/css/jquery-ui-1.12.1.min.css" rel="stylesheet">
    </head>
    <body style="overflow:scroll">
        <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 border-bottom">
                <h1 class="h2">디바이스 등록</h1>
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
                        <!-- 히든 요소 추가 -->
                        <input type="hidden" id="vehicleId" name="vehicleId" required>
                        <input type="hidden" id="hndsetId" name="hndsetId" required>
                        <div class="row">
                            <div class="col-md-5 mb-3">
                                <label for="cmpnId">회사명</label>
                                <select class="custom-select d-block w-100" id="cmpnId" required>
                                </select>
                                <div class="invalid-feedback">
                                        Please enter your company name.
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5 mb-3">
                                <label for="mfgComId">디바이스 제조사</label>
                                <select class="custom-select d-block w-100" id="mfgComId" required>
                                    <option value="">선택</option>
                                </select>
                                <div class="invalid-feedback">
                                        Please select a manufacturer company name.
                                </div>
                            </div>
                            <div class="col-md-5 mb-3">
                                <label for="dvcMdlId">디바이스 모델명</label>
                                <select class="custom-select d-block w-100" id="dvcMdlId" required>
                                    <option value="">선택</option>
                                </select>
                                <div class="invalid-feedback">
                                        Please enter your device model name.
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5 mb-3">
                                <label for="vehicleNum">디바이스 시리얼 번호</label>
                                <input type="text" class="form-control" id="dvcSn" name="dvcSn" data-toggle="modal" data-target="#vehicleModal" required>
                                <div class="invalid-feedback">
                                        Please enter your vehicle identification number.
                                </div>
                            </div>
                        </div>
                        </div>
                        <hr class="mb-3">
                        <div class="row">
                            <div class="col-md-12 mb-3">
                                <button class="btn btn-primary btn-block" type="button" id="fmDeviceInsertSubmit">등 록 하 기</button>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        <script src="/js/common/form-validation.js"></script>
        <script src="/plugins/jqgrid/4.4.3/js/i18n/grid.locale-en.js"></script>
        <script src="/plugins/jqgrid/4.4.3/js/jquery.jqGrid.src.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                // 콤보박스 초기화
                initComboBox();
                $("#cmpnId").attr("disabled",true);

                // 등록 버튼
                $("#fmDeviceInsertSubmit").click(function() {
                    checkFormValidate($("#fmDeviceInsert"), function() {
                        var sendData = {
                            cmpnId: $("#cmpnId").val(),
                            dvcMdlId: $("#dvcMdlId").val(),
                            dvcSn: $("#dvcSn").val()
                        }
                        fnCommPostCall("device_stock_data_exist", sendData, function(data) {
                            if(data) {
                                alert("중복된 시리얼 번호가 존재합니다.");
                            } else {
                                fnCommPostCall("device_stock_data_insert", sendData, function(data) {
                                    var moveUrl = function() {
                                        return findUrl("device_stock_view_detail") + "?dvcMdlId=" + data.dvcMdlId;
                                    }
                                    locationUrlHandler(moveUrl);
                                });
                            }
                        });
                    });
                });

                // 목록 버튼
                $("#btnList").click(function() {
                    // 디바이스 설치 화면으로 이동
                    locationUrl("device_stock_view_list");
                });

                // 제조회사 선택 콤보박스
                $("#mfgComId").change(function(element) {
                    var mfgComId = $(this).val();
                    if(mfgComId != "") {
                        fnDeviceModelList(mfgComId);
                    }
                });
            });

            function initComboBox() {
                var cmpnId = "${cmpnId}";
                if(cmpnId != "") {
                    fnCmpnList(cmpnId);
                }
                fnMfgList();
            }

            // 디바이스 제조회사 콤보박스 조회
            function fnMfgList() {
                fnCommPostCall("common_data_mfg_list", {}, function(data) {
                    data.forEach(element => {
                        $("select#mfgComId").append(
                            '<option value="' + element.mfgComId + '">' + element.mfgComNm + '</option>'
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
