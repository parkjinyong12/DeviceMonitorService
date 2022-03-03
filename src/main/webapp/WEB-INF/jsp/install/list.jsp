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

        <link href="/plugins/jqgrid/4.4.3/css/ui.jqgrid.css" rel="stylesheet">
        <link href="/plugins/jquery/css/jquery-ui-1.12.1.min.css" rel="stylesheet">

    </head>
    <body style="overflow:scroll">
        <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 border-bottom">
                <h1 class="h2">디바이스</h1>
            </div>
            <div class="starter-template container">
                <div class="order-md-1 mb-2">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center mb-2">
                        <h3 class="mb-0 mt-2">조회</h3>
                        <div class="btn-group mr-2">
                            <button class="btn btn-outline-primary" type="button" id="btnInsert">등록</button>
                        </div>
                    </div>
                    <div>
                        <table id="mainGrid"></table>
                        <div id="pager"></div>
                    </div>
                </div>
                <div style="text-align:right">
                    <div class="btn-group mr-2">
                        <button type="button" id="btnProcessStatus" class="btn btn-outline-secondary">요청</button>
                        <button type="button" id="btnDeleteStatus" class="btn btn-outline-secondary">삭제</button>
                    </div>
                </div>
            </div>
        </main>
        <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        <script src="/plugins/jqgrid/4.4.3/js/i18n/grid.locale-en.js"></script>
        <script src="/plugins/jqgrid/4.4.3/js/jquery.jqGrid.src.js"></script>
        <script type="text/javascript">
            // 초기에는 조회모드
            var controlStatus = "detail";

            $(document).ready(function(){
                // 초기 설정
                $('#mainGrid').jqGrid({
                    url: '/data/install/page',
                    datatype: "json",
                    height: 225,
                    width: 1100,
                    colNames: ['디바이스 관리 ID', '회사명', '이동수단 식별번호', '설치 상태코드', '설치 상태', '처리 시간'],
                    colModel: [
                        {name:'dvcId',           index:'dvcId',       align:'center', hidden:true,  title:false},
                        {name:'cmpnNm',          index:'cmpnNm',         align:'center', width:'25%',   title:false},
                        {name:'vehicleNum',      index:'vehicleNum',     align:'center', width:'25%',   title:false},
                        {name:'dvcIslSt',        index:'dvcIslSt',    align:'center', hidden:true,  title:false},
                        {name:'dvcIslStNm',      index:'dvcIslStNm',    align:'center', width:'25%',    title:false},
                        {name:'dvcIslStRegDt',   index:'dvcIslStRegDt',    align:'center', width:'25%', title:false}
                    ],
                    rowNum : 10,
                    pager: '#pager',
                    jsonReader: {
                         repeatitems:false
                    },
                    loadui: 'diable',
                    onSelectRow: function(rowid, status, e) {
                        if(controlStatus == "detail") {
                            var row = $(this).getRowData(rowid);
                            var moveUrl = function() {
                                return findUrl("install_view_detail") + "?dvcId=" + row.dvcId;
                            }
                            locationUrlHandler(moveUrl);
                        } else if(controlStatus == "request") {
                            var row = $(this).getRowData(rowid);
                            console.log(row);
                            if(confirm("해당 디바이스를 설치요청을 하시겠습니까?")) {
                                // 접수 하기
                                fnCommPostCall("install_data_request", {
                                    dvcId: row.dvcId,
                                    dvcIslSt: row.dvcIslSt
                                }, function(data) {
                                    if("1001" == data) {
                                        alert("요청이 전달되었습니다.");
                                        $("#mainGrid").trigger("reloadGrid");
                                        controlStatus = 'detail';
                                        changeAttrClass($("#btnProcessStatus"), "btn-outline-primary", "btn-outline-secondary");
                                    } else if("1003" == data) {
                                        alert("이미 설치요청된 디바이스 입니다.");
                                    } else {
                                        alert("처리가 진행되지 않았습니다.");
                                    }
                                });
                            }
                        } else if(controlStatus == "delete") {
                            var row = $(this).getRowData(rowid);
                            if(confirm("해당 데이터를 삭제하시겠습니까?")) {
                                fnDelete(row.dvcId);
                            }
                        }
                    }
                });
                $("#btnProcessStatus").click(function() {
                    if(controlStatus == 'detail') {
                        controlStatus = 'request';
                        changeAttrClass(this, "btn-outline-secondary", "btn-outline-primary");
                    } else if (controlStatus == 'request') {
                        controlStatus = 'detail';
                        changeAttrClass(this, "btn-outline-primary", "btn-outline-secondary");
                    } else if (controlStatus == 'delete') {
                        controlStatus = 'request';
                        changeAttrClass(this, "btn-outline-secondary", "btn-outline-primary");
                        changeAttrClass($("#btnDeleteStatus"), "btn-outline-primary", "btn-outline-secondary");
                    }
                });
                $("#btnDeleteStatus").click(function() {
                    if(controlStatus == 'detail') {
                        controlStatus = 'delete';
                        changeAttrClass(this, "btn-outline-secondary", "btn-outline-primary");
                    } else if (controlStatus == 'delete') {
                        controlStatus = 'detail';
                        changeAttrClass(this, "btn-outline-primary", "btn-outline-secondary");
                    } else if (controlStatus == 'request') {
                        controlStatus = 'delete';
                        changeAttrClass(this, "btn-outline-secondary", "btn-outline-primary");
                        changeAttrClass($("#btnProcessStatus"), "btn-outline-primary", "btn-outline-secondary");
                    }
                });

                // 등록 버튼
                $("#btnInsert").click(function() {
                   locationUrl("device_view_insert");
                });
            });

            function fnDelete(dvcId) {
                fnCommPostCall("install_data_delete", {
                    dvcId: dvcId
                }, function(data) {
                    locationUrl("install_view_list");
                });
            }
        </script>
    </body>
</html>
