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
                <h1 class="h2">디바이스 재고</h1>
            </div>
            <div class="starter-template container">
                <div class="order-md-1 ">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mb-2">
                        <h3 class="mb-0 mt-2">상세</h3>
                        <div class="btn-group mr-2">
                            <button class="btn btn-outline-secondary" type="button" id="fnDeviceList">목록</button>
                        </div>
                    </div>
                    <form id="fmDeviceUpdate" class="needs-validation" novalidate>
                    <!-- 히든 요소 추가 -->
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
                    </form>
                    <hr class="mt-4 mb-3">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mb-2">
                        <h3 class="mb-0 mt-2">재고이력</h3>
                        <div class="btn-group mr-2">
                            <button class="btn btn-outline-primary" type="button" data-toggle="modal" data-target="#dvcStkRegistModal">등록</button>
                            <button class="btn btn-outline-secondary" type="button" id="btnDelete">삭제</button>
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
        <div class="modal fade" id="dvcStkModal" tabindex="-1" role="dialog" aria-labelledby="dvcStkModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="dvcStkModalLabel">재고 상세</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="fmUpdate" class="needs-validation" novalidate>
                        <!-- 히든 요소 추가 -->
                        <input type="hidden" id="dvcStkIdModal" name="dvcStkIdModal" required>
                        <div class="mb-3">
                            <label for="dvcSnModal">디바이스 시리얼 번호</label>
                            <input type="hidden" class="form-control" id="dvcSnModalTemp" name="dvcSnModalTemp" >
                            <input type="text" class="form-control" id="dvcSnModal" name="dvcSnModal" required>
                            <div class="invalid-feedback">
                                    Please select a vehicle type name.
                            </div>
                        </div>
                        <div style="text-align:right">
                            <div class="btn-group mr-2">
                                <button class="btn btn-outline-primary" type="button" id="btnUpdateDvcStkModal">수정</button>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="dvcStkRegistModal" tabindex="-1" role="dialog" aria-labelledby="dvcStkRegistModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="dvcStkRegistModalLabel">재고 등록</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="fmRegist" class="needs-validation" novalidate>
                        <!-- 히든 요소 추가 -->
                        <input type="hidden" id="dvcStkIdModal" name="dvcStkIdModal" required>
                        <div class="mb-3">
                            <label for="dvcSnModal">디바이스 시리얼 번호</label>
                            <input type="text" class="form-control" id="dvcSnModal" name="dvcSnModal" required>
                            <div class="invalid-feedback">
                                    Please select a vehicle type name.
                            </div>
                        </div>
                        <div class="mb-1">
                            <table id="deviceStockRegistGrid"></table>
                            <div id="deviceStockRegistPager" style="display:none"></div>
                        </div>
                        <button class="btn btn-primary btn-block" type="button" id="fmRegistSubmit">등 록 하 기</button>
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
                fnUpdateInactive(false);

                initDeviceStockGrid();
                initDeviceStockRegistGrid();

                // 목록 버튼
                $("#fnDeviceList").click(function() {
                    locationUrl("device_stock_view_list");
                });
                // 접수 하기
                $("#fnReceipt").click(function() {
                    var dvcId = $("#dvcId").val();
                    var moveUrl = function() {
                        return findUrl("install_view_detail") + "?dvcId=" + dvcId;
                    }
                    locationUrlHandler(moveUrl);
                });
                // 제조회사 콤보박스 변경 이벤트
                $("#mfgComId").change(function(element) {
                    var mfgComId = $(this).val();
                    if(mfgComId != "") {
                        fnDeviceModelList(mfgComId);
                    }
                });

                // 디바이스 재고 상세 모달 내 수정 버튼
                $("#btnUpdateDvcStkModal").click(function() {
                    if(confirm("시리얼 번호를 수정하시겠습니까?")) {
                        fnUpdate();
                    }
                });

                // 디바이스 재고 상세 모달 내 삭제 버튼
                $("#btnDelete").click(function() {
                    if(confirm("시리얼 번호를 삭제하시겠습니까?")) {
                        fnUpdate();
                    }
                });

                // 디바이스 재고 수정 모달, 엔터 이벤트
                $("#fmUpdate #dvcSnModal").on("keydown",
                    function(key){
                        if(key.keyCode==13) {
                            if($(this).val() != "") {
                                fnUpdate();

                                // form submit 방지
                                return false;
                            }
                        }
                    }
                );

                // 디바이스 재고 등록 모달 내 등록 버튼
                $("#fmRegistSubmit").click(function() {
                    var allRowData = $("#deviceStockRegistGrid").getRowData();
                    fnCommPostCall("device_stock_data_insert_many", allRowData , function(data) {
                        $("#fmRegist #dvcSnModal").val('');
                        $("#dvcStkRegistModal").modal("hide");
                        $('#mainGrid').trigger("reloadGrid");
                    });
                });

                // 디바이스 재고 등록 모달 열릴때, 이벤트
                $('#dvcStkRegistModal').on('show.bs.modal', function (event) {
                    $("#fmRegist #dvcSnModal").val('');
                    $("#deviceStockRegistGrid").jqGrid('clearGridData');
                });

                var controlStatus = "detail";

                $("#btnDelete").click(function() {
                    if(controlStatus == 'detail') {
                        // 삭제 모드 진입. 버튼 색상(secondary-> primary)
                        controlStatus = 'delete';
                        changeAttrClass(this, "btn-outline-secondary", "btn-outline-primary");
                    } else if (controlStatus == 'delete') {
                        // 조회 모드 진입. 버튼 색상(primary -> secondary)
                        controlStatus = 'detail';
                        changeAttrClass(this, "btn-outline-primary", "btn-outline-secondary");
                    }
                });
            });

            // 디바이스 재고 등록 모달, 엔터 이벤트
            $("#fmRegist #dvcSnModal").on("keyup",
                function(key){
                    if(key.keyCode==13) {
                        if($(this).val() != "") {
                            var maxRowId = $("#deviceStockRegistGrid").getGridParam("reccount");
                            var sendData = {
                                cmpnId: $("#cmpnId").val(),
                                dvcMdlId: $("#dvcMdlId").val(),
                                dvcSn: $(this).val()
                            };
                            fnCommPostCall("device_stock_data_exist", sendData, function(data) {
                                if(data) {
                                    alert("중복된 시리얼 번호가 존재합니다.");
                                    $("#fmRegist #dvcSnModal").val('');
                                } else {
                                    $("#deviceStockRegistGrid").jqGrid("addRowData", maxRowId, sendData, 'last');
                                    $("#fmRegist #dvcSnModal").val('');
                                }
                            });
                        }
                    }
                }
            );
            // 상세회면 비활성화
            function fnUpdateInactive(fgRefresh) {
                if(fgRefresh) {
                    fnDetail();
                }
                fnElemDisabledById(["cmpnId", "mfgComId","dvcMdlId"]);
            };
            // 상세화면 활성화
            function fnUpdateActive() {
                fnElemDisabledById(["cmpnId"]);
                fnElemUnDisabledById(["mfgComId","dvcMdlId","vehicleNum","hndsetNum","hndsetMntSt"]);
            };

            function fnDetail() {
                fnCommPostCall("device_stock_data_detail", {
                    dvcMdlId: "${dvcMdlId}"
                }, function(data) {
                    fnCmpnList(data.cmpnId);
                    fnMfgList(data.mfgComId);
                    fnDeviceModelList(data.mfgComId, data.dvcMdlId);
                });
            }

            function fnUpdate() {
                checkFormValidate($("#fmUpdate"), function() {
                    var sendData = {
                        cmpnId: $("#cmpnId").val(),
                        dvcMdlId: $("#dvcMdlId").val(),
                        dvcSn: $("#fmUpdate #dvcSnModal").val(),
                        dvcStkId: $("#dvcStkIdModal").val()
                    }
                    if($("#fmUpdate #dvcSnModal").val() == $("#fmUpdate #dvcSnModalTemp").val()) {
                        alert("기존과 내용이 동일합니다.");
                    } else {
                        fnCommPostCall("device_stock_data_exist", sendData, function(data) {
                            if(data) {
                                alert("중복된 시리얼 번호가 존재합니다.");
                            } else {
                                fnCommPostCall("device_stock_data_update", sendData, function(data) {
                                    $("#fmUpdate #dvcSnModal").val('');
                                    $("#dvcStkModal").modal("hide");
                                    $('#mainGrid').trigger("reloadGrid");
                                });
                            }
                        });
                    };
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

            // 제조회사 콤보박스(mfgComId이 들어오면, selected됨)
            function fnMfgList(mfgComId) {

                var selectBox = $("select#mfgComId");
                initSelectBox(selectBox);

                fnCommPostCall("common_data_mfg_list", {}, function(data) {
                    data.forEach(element => {
                        $(selectBox).append(
                            '<option value="' + element.mfgComId + '">' + element.mfgComNm + '</option>'
                        );
                    });
                    // 제조회사 세팅
                    if(mfgComId != "") {
                        $(selectBox).val(mfgComId);
                    }
                });
            }

            // 디바이스 모델 콤보박스(제조사별)(dvcMdlId 들어오면, selected됨)
            function fnDeviceModelList(mfgComId, dvcMdlId) {

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
                    // 디바이스 모델 세팅
                    if(dvcMdlId != "") {
                        $(selectBox).val(dvcMdlId);
                    }
                });
            }

            function initDeviceStockGrid() {
                var gridUrl = findUrl("device_stock_data_page_device_model_detail") + "?dvcMdlId=${dvcMdlId}";
                // 초기 설정
                $('#mainGrid').jqGrid({
                    url: gridUrl,
                    datatype: "json",
                    height: 225,
                    width: 1100,
                    colNames: ['디바이스 재고 ID', '디바이스 모델명', '디바이스 시리얼 번호', '수정 일자'],
                    colModel: [
                        {name:'dvcStkId',           index:'dvcStkId',       align:'center', hidden:true,    title:false},
                        {name:'dvcMdlNm',           index:'dvcMdlNm',       align:'center', width:'33%',    title:false},
                        {name:'dvcSn',              index:'dvcSn',          align:'center', width:'33%',    title:false},
                        {name:'regDt',              index:'regDt',          align:'center', width:'33%',    title:false}
                    ],
                    rowNum : 10,
                    pager: '#pager',
                    jsonReader: {
                        repeatitems:false
                    },
                    onSelectRow: function(rowid, status, e) {
                        var row = $(this).getRowData(rowid);
                        $("#dvcSnModal").val(row.dvcSn);
                        $("#dvcSnModalTemp").val(row.dvcSn);
                        $("#dvcStkIdModal").val(row.dvcStkId);
                        $("#dvcStkModal").modal("show");
                    },
                    loadui: 'diable'
                });
            }

            function initDeviceStockRegistGrid() {

                var dummyData = [];
                // 초기 설정
                $('#deviceStockRegistGrid').jqGrid({
                    data: dummyData,
                    datatype: "json",
                    height: 225,
                    width: 466,
                    colNames: ['회사 ID', '디바이스 모델 ID', '디바이스 시리얼 번호'],
                    colModel: [
                        {name:'cmpnId',             index:'cmpnId',         align:'center', hidden:true,    title:false},
                        {name:'dvcMdlId',           index:'dvcMdlId',       align:'center', hidden:true,    title:false},
                        {name:'dvcSn',              index:'dvcSn',          align:'center', width:'100%',    title:false}
                    ],
                    rowNum : 10,
                    pager: '#deviceStockRegistPager',
                    jsonReader: {
                        repeatitems:false
                    },
                    ondblClickRow: function(rowid, status, e) {
                        $("#deviceStockRegistGrid").jqGrid("delRowData", rowid);
                    },
                    loadui: 'diable'
                });
            }
        </script>
    </body>
</html>
