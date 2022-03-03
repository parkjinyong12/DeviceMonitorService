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
                <h1 class="h2">디바이스 설치</h1>
            </div>
            <div class="starter-template container">
                <div class="order-md-1 ">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mb-2">
                        <h3 class="mb-0 mt-2">설치 이력</h3>
                        <div class="btn-group mr-2" id="btn-group-1">
                            <button class="btn btn-outline-primary" type="button" id="fnDeviceUpdate">수정</button>
                            <button class="btn btn-outline-secondary" type="button" id="fnDeviceList">목록</button>
                        </div>
                        <div class="btn-group mr-2" id="btn-group-2">
                            <button class="btn btn-outline-primary" type="button" id="fnDeviceUpdateSubmit">완료</button>
                            <button class="btn btn-outline-secondary" type="button" id="fnDeviceUpdateCancel">취소</button>
                        </div>
                    </div>
                    <!-- 히든 요소 추가 -->
                    <input type="hidden" id="dvcId" name="dvcId" value="${dvcId}" required>
                    <input type="hidden" id="vehicleId" name="vehicleId" required>
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
                                    Please enter your device phone number.
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5 mb-3">
                            <label for="hndsetNum">유심 전화번호</label>
                            <input type="text" class="form-control" id="hndsetNum" name="hndsetNum" data-toggle="modal" data-target="#hndsetOpenModal" required>
                            <div class="invalid-feedback">
                                    Please enter your device phone number.
                            </div>
                        </div>
                        <div class="col-md-5 mb-3">
                            <label for="hndsetMntSt">유심 장착 상태</label>
                            <select class="custom-select d-block w-100" id="hndsetMntSt" required>
                                <option value="">선택</option>
                            </select>
                            <div class="invalid-feedback">
                                    Please enter your device phone number.
                            </div>
                        </div>
                    </div>
                    <hr class="mt-4 mb-3">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mb-2">
                        <h3 class="mb-0 mt-2">설치 이력</h3>
                        <div class="btn-group mr-2">
                            <button class="btn btn-outline-secondary" type="button" data-toggle="modal" data-target="#hndsetOpenRegistModal">이력 변경</button>
                        </div>
                    </div>
                    <div class="mb-3">
                        <table id="mainGrid"></table>
                        <div id="pager"></div>
                    </div>
                    <div style="text-align:right">
                        <div class="btn-group mr-2">
                            <button class="btn btn-outline-primary" type="button" id="btnInstallRequest">설치 요청</button>
                        </div>
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
                        <table id="vehicleModalGrid"></table>
                        <div id="vehicleModalPaper"></div>
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
                fnUpdateInactive(true);

                // 차량 조회 모달 그리드 초기화
                // initVehicleModelGrid();
                $("#btn-group-2").hide();

                // 목록으로 돌아가기
                $("#fnDeviceList").click(function() {
                    var url = "/view/install-confirm/list";
                    $(location).attr('href',url);
                });
                // 접수 하기
                $("#btnInstallRequest").click(function() {
                    // 보내는 데이터
                    var sendData = {
                        dvcId: $("#dvcId").val()
                    }
                    $.ajax({
                        url : '/data/install-confirm/request',
                        type : 'POST',
                        contentType: 'application/json',
                        data : JSON.stringify(sendData),
                        success : function(data) {
                            // var url = "/view/install-confirm/list";
                            // $(location).attr('href',url);
                            // fnUpdateInactive(true);
                            // 그리드 리로딩
                            $("#mainGrid").trigger("reloadGrid");
                        },
                        error : function(xhr, status) {
                            alert(xhr + " : " + status);
                        }
                    });
                });
                // 수정하기
                $("#fnDeviceUpdateSubmit").click(function() {
                    var fmDeviceUpdate = $("#fmDeviceUpdate");
                    if(fmDeviceUpdate.isValid()) {
                        var dvcId = $("#dvcId").val();
                        // 보내는 데이터
                        var sendData = {
                            dvcId: dvcId,
                            cmpnId: $("#cmpnId").val(),
                            hndsetId: $("#hndsetId").val(),
                            dvcMdlId: $("#dvcMdlId").val(),
                            vehicleId: $("#vehicleId").val()
                        }
                        $.ajax({
                            url : '/data/install-confirm/update',
                            type : 'POST',
                            contentType: 'application/json',
                            data : JSON.stringify(sendData),
                            success : function(data) {
                                var url = "/view/install-confirm/detail";
                                $(location).attr('href',url);
                                fnUpdateInactive(false);
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
                    fnUpdateInactive(true);
                });
                // 수정하기(활성화)
                $("#fnDeviceUpdate").click(function() {
                    fnUpdateActive();
                    $("#btn-group-1").hide();
                    $("#btn-group-2").show();
                    $("#cmpnId").focus();
                });
                // 제조회사 콤보박스 변경 이벤트
                $("#mfgComId").change(function(element) {
                    var mfgComId = $(this).val();
                    if(mfgComId != "") {
                        fnDeviceModelList(mfgComId);
                    }
                });
            });
            // 상세회면 비활성화
            function fnUpdateInactive(refreshYn) {
                $("#btn-group-1").show();
                $("#btn-group-2").hide();
                if(refreshYn) {
                    fnDetail();
                    initDvcIslHistGrid();
                }
                $("#cmpnId").attr("disabled",true);
                $("#mfgComId").attr("disabled",true);
                $("#dvcMdlId").attr("disabled",true);
                $("#vehicleNum").attr("disabled",true);
                $("#hndsetNum").attr("disabled",true);
                $("#hndsetMntSt").attr("disabled",true);
            };
            // 상세화면 활성화
            function fnUpdateActive() {
                // $("#cmpnId").removeAttr("disabled");
                $("#mfgComId").removeAttr("disabled");
                $("#dvcMdlId").removeAttr("disabled");
                $("#vehicleNum").removeAttr("disabled");
                $("#hndsetNum").removeAttr("disabled");
                $("#hndsetMntSt").removeAttr("disabled");
            };

            function initDvcIslHistGrid() {
                var dvcId = $("#dvcId").val();
                // 초기 설정
                $('#mainGrid').jqGrid({
                    url: '/data/install-confirm/detail?dvcId='+dvcId,
                    datatype: "json",
                    height: 225,
                    width: 1100,
                    colNames: ['디바이스 설치이력 ID', '이동수단 식별번호', '설치 상태', '처리 일자'],
                    colModel: [
                        {name:'dvcIslHistId',           index:'dvcIslHistId',       align:'center', hidden:true,    title:false},
                        {name:'vehicleNum',         index:'vehicleNum',     align:'center', width:'33%',    title:false},
                        {name:'dvcIslStNm',     index:'dvcIslStNm',    align:'center', width:'33%', title:false},
                        {name:'regDt',           index:'regDt',       align:'center', width:'33%',  title:false}
                    ],
                    rowNum : 10,
                    pager: '#pager',
                    jsonReader: {
                        repeatitems:false
                    },
                    onSelectRow: function(rowid, status, e) {
                        if(controlStatus == "detail") {
                            var row = $(this).getRowData(rowid);
                            var url = "/view/install-confirm/detail?dvcId=" + row.dvcId;
                            $(location).attr('href',url);
                        } else if(controlStatus == "delete") {
                            var row = $(this).getRowData(rowid);
                            if(confirm("해당 데이터를 삭제하시겠습니까?")) {
                                fnDelete(row.dvcId);
                            }
                        }
                    }
                });
            }

            function fnDetail() {
                var sendData = {
                    dvcId: $("#dvcId").val()
                };
                $.ajax({
                    url : '/data/device/detail',
                    type : 'POST',
                    contentType: 'application/json',
                    data : JSON.stringify(sendData),
                    success : function(data) {
                        fnCmpnList(data.cmpnId);

                        fnMfgList(data.mfgComId);
                        fnDeviceModelList(data.mfgComId, data.dvcMdlId);
                        fnHndsetMntStList(data.hndsetMntSt);
                        $("#vehicleId").val(data.vehicleId);
                        $("#vehicleNum").val(data.vehicleNum);
                        $("#hndsetNum").val(data.hndsetNum);
                        $("#hndsetId").val(data.hndsetId);
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

            // 제조회사 콤보박스(mfgComId이 들어오면, selected됨)
            function fnMfgList(mfgComId) {
                // option 초기화
                $("select#mfgComId").children('option').remove();
                $("select#mfgComId").append('<option value="">제조사 선택...</option>');

                $.ajax({
                    url : '/data/common/mfg-list',
                    type : 'POST',
                    contentType: 'application/json',
                    success : function(data) {
                        data.forEach(element => {
                            $("select#mfgComId").append(
                                '<option value="' + element.mfgComId + '">' + element.mfgComNm + '</option>'
                            );
                        });
                        // 제조회사 세팅
                        if(mfgComId != "") {
                            $("select#mfgComId").val(mfgComId);
                        }
                    },
                    error : function(xhr, status) {
                        alert(xhr + " : " + status);
                    }
                });
            }

            // 디바이스 모델 콤보박스(제조사별)(dvcMdlId 들어오면, selected됨)
            function fnDeviceModelList(mfgComId, dvcMdlId) {
                // option 초기화
                $("select#dvcMdlId").children('option').remove();
                $("select#dvcMdlId").append('<option value="">디바이스 모델명 선택...</option>');
                var sendData = {
                    mfgComId: mfgComId
                }
                $.ajax({
                    url : '/data/device-model/combo',
                    type : 'POST',
                    data : JSON.stringify(sendData),
                    contentType: 'application/json',
                    success : function(data) {
                        data.forEach(element => {
                            $("select#dvcMdlId").append(
                                '<option value="' + element.dvcMdlId + '">' + element.dvcMdlNm + '</option>'
                            );
                        });
                        // 디바이스 모델 세팅
                        if(dvcMdlId != "") {
                            $("select#dvcMdlId").val(dvcMdlId);
                        }
                    },
                    error : function(xhr, status) {
                        alert(xhr + " : " + status);
                    }
                });
            }

            // 디바이스 장착상태 콤보박스
            function fnHndsetMntStList(hndsetMntSt) {
                // option 초기화
                $("select#hndsetMntSt").children('option').remove();
                $("select#hndsetMntSt").append('<option value="">선택</option>');

                $.ajax({
                    url : '/data/code/hndset-mnt-st-list',
                    type : 'GET',
                    contentType: 'application/json',
                    success : function(data) {
                        data.forEach(element => {
                            $("select#hndsetMntSt").append(
                                '<option value="' + element.cd + '">' + element.cdDc + '</option>'
                            );
                            $("select#hndsetMntSt").val(hndsetMntSt);
                        });
                    },
                    error : function(xhr, status) {
                        alert(xhr + " : " + status);
                    }
                });
            }

            function initVehicleModelGrid() {
                // 초기 설정
                $('#vehicleModalGrid').jqGrid({
                    url: '/data/vehicle/page',
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
                    pager: '#vehicleModalPaper',
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
            }
        </script>
    </body>
</html>
