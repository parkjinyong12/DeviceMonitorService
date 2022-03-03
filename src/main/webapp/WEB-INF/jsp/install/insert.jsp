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
                        <div class="mb-3">
                            <label for="cmpnId">회사명</label>
                            <select class="custom-select d-block w-100" id="cmpnId" required>
                            </select>
                            <div class="invalid-feedback">
                                    Please enter your company name.
                            </div>
                        </div>
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
                            <label for="dvcMdlId">디바이스 모델명</label>
                            <select class="custom-select d-block w-100" id="dvcMdlId" required>
                                <option value="">선택</option>
                            </select>
                            <div class="invalid-feedback">
                                    Please enter your device model name.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="vehicleNum">이동수단 식별번호</label>
                            <input type="text" class="form-control" id="vehicleNum" name="vehicleNum" placeholder="50보4304" data-toggle="modal" data-target="#vehicleModal" required>
                            <div class="invalid-feedback">
                                    Please enter your vehicle identification number.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="hndsetId">디바이스 전화번호</label>
                            <input type="text" class="form-control" id="hndsetId" name="hndsetId" placeholder="01085671470" required>
                            <div class="invalid-feedback">
                                    Please enter your device phone number.
                            </div>
                        </div>
                        <hr class="mb-3">
                        <button class="btn btn-primary btn-block" type="button" id="fmDeviceInsertSubmit">등 록 하 기</button>
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
                // 콤보박스 초기화
                initComboBox();
                $("#cmpnId").attr("disabled",true);

                // 차량 조회 모달 그리드 초기화
                initVehicleModelGrid();

                // 등록 버튼
                $("#fmDeviceInsertSubmit").click(function() {
                    var fmDeviceInsert = $("#fmDeviceInsert");
                    if(fmDeviceInsert.isValid()) {
                        // 보내는 데이터
                        var sendData = {
                            cmpnId: $("#cmpnId").val(),
                            hndsetId: $("#hndsetId").val(),
                            dvcMdlId: $("#dvcMdlId").val(),
                            vehicleId: $("#vehicleId").val()
                        };
                        $.ajax({
                            url : '/data/device/insert',
                            type : 'POST',
                            contentType: 'application/json',
                            data : JSON.stringify(sendData),
                            success : function(data) {
                                var url = "/view/device/list";
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

                // 목록 버튼
                $("#btnList").click(function() {
                    var url = "/view/device/list";
                    $(location).attr('href',url);
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
                    },
                    error : function(xhr, status) {
                        alert(xhr + " : " + status);
                    }
                });
            }

            // 제조회사 별 디바이스 모델 콤보박스 조회
            function fnDeviceModelList(mfgComId) {
                // option 초기화
                $("select#dvcMdlId").children('option').remove();
                var defalutText = "선택";
                $("select#dvcMdlId").append('<option value="">' + defalutText + '</option>');

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
                    pager: '#vehicleModalPager',
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
