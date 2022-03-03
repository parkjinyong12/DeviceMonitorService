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
                            <button class="btn btn-outline-secondary" type="button" id="btnContinue">계속</button>
                            <button class="btn btn-outline-secondary" type="button" id="btnList">목록</button>
                        </div>
                    </div>
                    <form id="fmDeviceInsert" class="needs-validation" novalidate>
                        <!-- 히든 요소 추가 -->
                        <input type="hidden" id="vehicleId" name="vehicleId" required>
                        <input type="hidden" id="hndsetId" name="hndsetId" required>
                        <input type="hidden" id="isContinue" value="false">
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
                                <label for="vehicleNum">이동수단 식별번호</label>
                                <input type="text" class="form-control" id="vehicleNum" name="vehicleNum" data-toggle="modal" data-target="#vehicleModal" required>
                                <div class="invalid-feedback">
                                        Please enter your vehicle identification number.
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5 mb-3">
                                <label for="hndsetNum">유심 전화번호</label>
                                <input type="text" class="form-control" id="hndsetNum" name="hndsetNum" data-toggle="modal" data-target="#hndsetModal" required>
                                <div class="invalid-feedback">
                                        Please enter your device phone number.
                                </div>
                            </div>
                            <div class="col-md-5 mb-3">
                                <label for="hndsetMntSt">유심 장착상태</label>
                                <select class="custom-select d-block w-100" id="hndsetMntSt" required>
                                    <option value="">선택</option>
                                </select>
                                <div class="invalid-feedback">
                                        Please enter your device phone number.
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
        <!-- Modal -->
        <div class="modal fade" id="vehicleModal" tabindex="-1" role="dialog" aria-labelledby="vehicleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="vehicleModalLabel">이동수단 조회</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <input type="text" id="vehicleModalSearchText" class="form-control" placeholder="이동수단 식별번호">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" id="btnVehicleModalSearch">검색</button>
                            </div>
                        </div>
                        <table id="vehicleModalGrid"></table>
                        <div id="vehicleModalPager"></div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="hndsetModal" tabindex="-1" role="dialog" aria-labelledby="hndsetModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="hndsetModalLabel">전화번호 조회</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <input type="text" id="hndsetModalSearchText" class="form-control" placeholder="유심 전화번호">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" id="btnHndsetModalSearch">검색</button>
                            </div>
                        </div>
                        <table id="hndsetModalGrid"></table>
                        <div id="hndsetModalPager"></div>
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
                // 콤보박스 초기화
                initComboBox();
                $("#cmpnId").attr("disabled",true);

                console.log('${isContinue}');

                // 차량 모달 그리드
                initVehicleModelGrid();
                // 유심 전화번호 모달 그리드
                initHndsetGrid();

                // 등록 버튼
                $("#fmDeviceInsertSubmit").click(function() {
                    checkFormValidate($("#fmDeviceInsert"), function() {
                        fnCommPostCall("device_data_insert", {
                            cmpnId: $("#cmpnId").val(),
                            hndsetId: $("#hndsetId").val(),
                            dvcMdlId: $("#dvcMdlId").val(),
                            vehicleId: $("#vehicleId").val(),
                            hndsetMntSt: $("#hndsetMntSt").val()
                        }, function(data) {
                            if($("#isContinue").val() == "true") {
                                alert("등록이 완료되었습니다.");
                                var moveUrl = function() {
                                    return findUrl("device_view_insert") + "?isContinue=" + $("#isContinue").val();
                                }
                                locationUrlHandler(moveUrl);
                            } else {
                                var moveUrl = function() {
                                    return findUrl("install_view_detail") + "?dvcId=" + data.dvcId;
                                }
                                locationUrlHandler(moveUrl);
                            }
                        });
                    });
                });

                // 목록 버튼
                $("#btnList").click(function() {
                    // 디바이스 설치 화면으로 이동
                    locationUrl("install_view_list");
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

                // 제조회사 선택 콤보박스
                $("#mfgComId").change(function(element) {
                    var mfgComId = $(this).val();
                    if(mfgComId != "") {
                        fnDeviceModelList(mfgComId);
                    }
                });

                // 계속 등록진행 중
                if("${isContinue}" == "true") {
                    $("#btnContinue").trigger('click');
                }

                // 이동수단 조회 모달 내 검색 버튼
                $("#btnVehicleModalSearch").click(function() {
                    var searchText = $("#vehicleModalSearchText").val();
                    initVehicleModelGrid(function() {
                        var url = findUrl("vehicle_data_page_usable");
                        if(searchText) {
                            url += "?searchText=" + encodeURIComponent(searchText)
                        }
                        console.log("url: " + url)
                        return url;
                    });
                });

                // 이동수단 조회 모달 내 검색창 엔터 이벤트
                $("#vehicleModalSearchText").on("keyup",
                    function(key){
                        if(key.keyCode==13) {
                            $("#btnVehicleModalSearch").trigger('click');
                        }
                    }
                );

                // 이동수단 조회 모달 오픈 시, 포커스
                $("#vehicleModal").on("shown.bs.modal", function () {
                    $("#vehicleModalSearchText").focus();
                });

                // 이동수단 조회 모달 내 검색창 두번 클릭 시
                $("#vehicleModalSearchText").dblclick(function() {
                    $(this).val("");
                });

                // 유심 전화번호 조회 모달 내 검색 버튼
                $("#btnHndsetModalSearch").click(function() {
                    var searchText = $("#hndsetModalSearchText").val();
                    initHndsetGrid(function() {
                        var url = findUrl("hndset_data_page_usable");
                        if(searchText) {
                            url += "?searchText=" + encodeURIComponent(searchText)
                        }
                        console.log("url: " + url)
                        return url;
                    });
                });

                // 유심 전화번호 조회 모달 내 검색창 엔터 이벤트
                $("#hndsetModalSearchText").on("keyup",
                    function(key){
                        if(key.keyCode==13) {
                            $("#btnHndsetModalSearch").trigger('click');
                        }
                    }
                );

                // 유심 전화번호 조회 모달 오픈 시, 포커스
                $("#hndsetModal").on("shown.bs.modal", function () {
                    $("#hndsetModalSearchText").focus();
                });

                // 유심 전화번호 조회 모달 내 검색창 두번 클릭 시
                $("#hndsetModalSearchText").dblclick(function() {
                    $(this).val("");
                });
            });

            function initComboBox() {
                var cmpnId = "${cmpnId}";
                var hndsetMntSt = "${hndsetMntSt}";
                if(cmpnId != "") {
                    fnCmpnList(cmpnId);
                }
                fnMfgList();
                if(hndsetMntSt != "") {
                    fnHndsetMntStList(hndsetMntSt);
                }
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

            // 디바이스 장착상태 콤보박스
            function fnHndsetMntStList(hndsetMntSt) {
                fnCommGetCall(function() {
                    return findUrl("code_hndset_mount_status_list");
                }, function(data) {
                    data.forEach(element => {
                        $("select#hndsetMntSt").append(
                            '<option value="' + element.cd + '">' + element.cdDc + '</option>'
                        );
                        $("select#hndsetMntSt").val(hndsetMntSt);
                    });
                });
            }

            function initVehicleModelGrid(gridUrlHandler) {
                var gridUrl = findUrl("vehicle_data_page_usable");
                if(gridUrlHandler) {
                    gridUrl = gridUrlHandler();
                    $('#vehicleModalGrid').jqGrid('setGridParam', {datatype: 'json', url: gridUrl}).trigger("reloadGrid");
                } else {
                    // 초기 설정
                    $('#vehicleModalGrid').jqGrid({
                        url: gridUrl,
                        datatype: "json",
                        height: 225,
                        width: 466,
                        colNames: ['이동수단 id', '타입', '이동수단 명'],
                        colModel: [
                            { name:'vehicleId',             index:'vehicleId',            align:'center', hidden:true,  title:false},
                            { name:'vehicleTpNm',           index:'vehicleTpNm',          align:'center', width:'40%',  title:false},
                            { name:'vehicleNum',           index:'vehicleNum',          align:'center', width:'60%',    title:false}
                        ],
                        rowNum : 10,
                        pager: '#vehicleModalPager',
                        loadui: 'diable',
                        jsonReader: {
                             repeatitems:false
                        },
                        ondblClickRow: function(rowid, status, e) {
                            var row = $(this).getRowData(rowid);
                            $("#vehicleId").val(row.vehicleId);
                            $("#vehicleNum").val(row.vehicleNum);
                            $("#vehicleModal").modal("hide");
                        }
                    });
                };
            }

            function initHndsetGrid(gridUrlHandler) {
                var gridUrl = findUrl("hndset_data_page_usable");
                if(gridUrlHandler) {
                    gridUrl = gridUrlHandler();
                    $('#hndsetModalGrid').jqGrid('setGridParam', {datatype: 'json', url: gridUrl}).trigger("reloadGrid");
                } else {
                    $('#hndsetModalGrid').jqGrid({
                        url: gridUrl,
                        datatype: "json",
                        height: 225,
                        width: 466,
                        colNames: ['데이터유심 id', '유심 전화번호', '데이터 제공사', '개통 상태'],
                        colModel: [
                            { name:'hndsetId',              index:'hndsetId',           align:'center', hidden:true,    title:false},
                            { name:'hndsetNum',             index:'hndsetNum',          align:'center', width:'33%',    title:false},
                            { name:'hndsetCarrierNm',       index:'hndsetCarrierNm',    align:'center', width:'33%',    title:false},
                            { name:'hndsetOpenStNm',        index:'hndsetOpenStNm',     align:'center', width:'33%',    title:false}
                        ],
                        rowNum : 10,
                        pager: '#hndsetModalPager',
                        jsonReader: {
                             repeatitems:false
                        },
                        ondblClickRow: function(rowid, status, e) {
                            var row = $(this).getRowData(rowid);
                            $("#hndsetId").val(row.hndsetId);
                            $("#hndsetNum").val(row.hndsetNum);
                            $("#hndsetModal").modal("hide");
                        }
                    });
                }
            }
        </script>
    </body>
</html>
