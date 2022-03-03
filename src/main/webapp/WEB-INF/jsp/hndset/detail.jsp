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
                <h1 class="h2">데이터 유심</h1>
            </div>
            <div class="starter-template container">
                <div class="order-md-1">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mb-2">
                        <h3 class="mb-0 mt-2">상세</h3>
                        <div class="btn-group mr-2">
                            <button class="btn btn-outline-secondary" type="button" id="fnDeviceList">목록</button>
                        </div>
                    </div>
                    <form id="fmDeviceUpdate" class="needs-validation" novalidate>
                    <!-- 히든 요소 추가 -->
                    <input type="hidden" id="hndsetId" name="hndsetId" value="${hndsetId}" required>
                    <div class="mb-3">
                        <label for="cmpnId">회사명</label>
                        <select class="custom-select d-block w-100" id="cmpnId" required>
                        </select>
                        <div class="invalid-feedback">
                                Please enter your company name.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="hndsetCarrierCd">데이터 유심 제공사</label>
                        <select class="custom-select d-block w-100" id="hndsetCarrierCd" required>
                            <option value="">선택</option>
                        </select>
                        <div class="invalid-feedback">
                                Please select a vehicle type name.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="hndsetNum">디바이스 전화번호</label>
                        <input type="text" class="form-control" id="hndsetNum" name="hndsetNum" placeholder="01285671470" required>
                        <div class="invalid-feedback">
                                Please enter your handset number.
                        </div>
                    </div>
                    <div class="row">
                         <div class="col-md-12 mb-3">
                            <label for="hndsetOpenSt">디바이스 개통상태</label>
                            <select class="custom-select d-block w-100" id="hndsetOpenSt" required>
                                <option value="">선택</option>
                            </select>
                            <div class="invalid-feedback">
                                    Please select a vehicle type name.
                            </div>
                        </div>
                    </div>
                    </form>
                    <hr class="mt-4 mb-3">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mb-2">
                        <h3 class="mb-0 mt-2">개통이력</h3>
                        <div class="btn-group mr-2">
                            <button class="btn btn-outline-secondary" type="button" data-toggle="modal" data-target="#hndsetOpenRegistModal">이력 변경</button>
                        </div>
                    </div>
                    <div class="mb-3">
                        <table id="mainGrid"></table>
                        <div id="pager"></div>
                    </div>
                </div>
            </div>
        </main>
        <!-- Modal -->
        <div class="modal fade" id="hndsetOpenRegistModal" style="overflow:hidden" tabindex="-1" role="dialog" aria-labelledby="hndsetOpenRegistModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="vehicleModalLabel">이력 변경</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="fmDeviceInsert" class="needs-validation" novalidate>
                        <div class="mb-3">
                            <label for="hndsetCarrierCdModal">데이터 유심 제공사</label>
                            <select class="custom-select d-block w-100" id="hndsetCarrierCdModal" required>
                                <option value="">선택</option>
                            </select>
                            <div class="invalid-feedback">
                                    Please select a vehicle type name.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="hndsetOpenStModal">디바이스 개통상태</label>
                            <select class="custom-select d-block w-100" id="hndsetOpenStModal" required>
                                <option value="">선택</option>
                            </select>
                            <div class="invalid-feedback">
                                    Please select a vehicle type name.
                            </div>
                        </div>
                        <button class="btn btn-primary btn-block" type="button" id="fmDeviceInsertSubmit">등 록 하 기</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        <script src="/js/common/form-validation.js"></script>
        <script src="/plugins/jqgrid/4.4.3/js/i18n/grid.locale-en.js"></script>
        <script src="/plugins/jqgrid/4.4.3/js/jquery.jqGrid.src.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                fnDetail();

                // 그리드
                initHndsetOpenGrid();

                $("#btn-group-2").hide();
                fnUpdateInactive();

                // 목록으로 돌아가기
                $("#fnDeviceList").click(function() {
                    locationUrl("hndset_view_list");
                });
                // 수정하기
                $("#fnDeviceUpdateSubmit").click(function() {
                    checkFormValidate($("#fmDeviceUpdate"), function() {
                        fnCommPostCall("hndset_data_update", {
                            hndsetId: $("#hndsetId").val(),
                            cmpnId: $("#cmpnId").val(),
                            hndsetNum: $("#hndsetNum").val(),
                            hndsetCarrierCd: $("#hndsetCarrierCd").val(),
                            hndsetOpenSt: $("#hndsetOpenSt").val()
                        }, function(data) {
                            locationUrl("hndset_view_list");
                        });
                    });
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
                    $("#btn-group-1").hide();
                    $("#btn-group-2").show();
                    fnUpdateActive();
                });

                // 개통이력 변경(이력등록) 버튼
                $("#fmDeviceInsertSubmit").click(function() {
                    checkFormValidate($("#fmDeviceInsert"), function() {
                        var hndsetId = $("#hndsetId").val();
                        fnCommPostCall("hndset_data_insert_open_hist", {
                            hndsetId: hndsetId,
                            hndsetCarrierCd: $("#hndsetCarrierCdModal").val(),
                            hndsetOpenSt: $("#hndsetOpenStModal").val()
                        }, function(data) {
                            var moveUrl = function() {
                                return findUrl("hndset_view_detail") + "?hndsetId=" + hndsetId;
                            }
                            locationUrlHandler(moveUrl);
                        });
                    });
                });
            });
            // 상세회면 비활성화
            function fnUpdateInactive() {
                fnElemDisabledById(["cmpnId", "hndsetCarrierCd", "hndsetNum", "hndsetOpenSt"]);
            };
            // 상세화면 활성화
            function fnUpdateActive() {
                fnElemUnDisabledById(["hndsetCarrierCd", "hndsetNum", "hndsetOpenSt"]);
            };

            function fnDetail() {
                fnCommPostCall("hndset_data_detail", {
                    hndsetId: $("#hndsetId").val()
                }, function(data) {
                    fnCmpnList(data.cmpnId)
                    fnHndsetOpenStList(data.hndsetOpenSt)
                    fnHndsetCarrierList(data.hndsetCarrierCd)
                    $("#hndsetNum").val(data.hndsetNum)
                });
            }

            function initHndsetOpenGrid() {
                var hndsetId = $("#hndsetId").val();
                // 초기 설정
                $('#mainGrid').jqGrid({
                    url: '/data/hndset/page/open-hist?hndsetId='+hndsetId,
                    datatype: "json",
                    height: 225,
                    width: 1100,
                    colNames: ['데이터유심 개통 ID', '데이터 제공사', '개통 상태', '처리 일자'],
                    colModel: [
                        {name:'hndsetOpenId',           index:'hndsetOpenId',       align:'center', hidden:true,    title:false},
                        {name:'hndsetCarrierNm',        index:'hndsetCarrierNm',    align:'center', width:'33%',    title:false},
                        {name:'hndsetOpenStNm',         index:'hndsetOpenStNm',     align:'center', width:'33%',    title:false},
                        {name:'regDt',                  index:'regDt',              align:'center', width:'33%',    title:false}
                    ],
                    rowNum : 10,
                    pager: '#pager',
                    jsonReader: {
                        repeatitems:false
                    },
                    onSelectRow: function(rowid, status, e) {

                    }
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

            // 데이터 유심 개통상태 콤보박스
            function fnHndsetOpenStList(hndsetOpenSt) {

                fnCommGetCall(function() {
                    return findUrl("code_hndset_open_status_list");
                }, function(data) {
                    // 메인 화면용
                    data.forEach(element => {
                        $("select#hndsetOpenSt").append(
                            '<option value="' + element.cd + '">' + element.cdDc + '</option>'
                        );
                    });

                    // 모달용
                    data.forEach(element => {
                        $("select#hndsetOpenStModal").append(
                            '<option value="' + element.cd + '">' + element.cdDc + '</option>'
                        );
                    });

                    // 개통상태 세팅
                    if(hndsetOpenSt != "") {
                        $("select#hndsetOpenSt").val(hndsetOpenSt);
                    }
                    if(hndsetOpenSt != "") {
                        $("select#hndsetOpenStModal").val(hndsetOpenSt);
                    }
                });
            }

            // 데이터 제공사 콤보박스
            function fnHndsetCarrierList(hndsetCarrierCd) {
                fnCommGetCall(function() {
                    return findUrl("code_hndset_carrier_list");
                }, function(data) {
                    // 메인 화면용
                    data.forEach(element => {
                        $("select#hndsetCarrierCd").append(
                            '<option value="' + element.cd + '">' + element.cdDc + '</option>'
                        );
                    });
                    // 모달용
                    data.forEach(element => {
                        $("select#hndsetCarrierCdModal").append(
                            '<option value="' + element.cd + '">' + element.cdDc + '</option>'
                        );
                    });
                    // 데이터 제공사 세팅
                    if(hndsetCarrierCd != "") {
                        $("select#hndsetCarrierCd").val(hndsetCarrierCd);
                    }
                    if(hndsetCarrierCd != "") {
                        $("select#hndsetCarrierCdModal").val(hndsetCarrierCd);
                    }
                });
            }
        </script>
    </body>
</html>
