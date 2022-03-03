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
        <style>
            #hiddenDivLoading {
                position: absolute;
                text-align: center;
                left: 50%;
                top: 50%;
                margin: -120px auto 0 -130px; /* value of top margin: height of the image divide by 2 (ie: 240 / 2), value of left margin: width of the image divide by 2 (ie: 260 / 2) */
                width: 85px; /* same as the image width */
            }
        </style>
    </head>
    <body style="overflow:scroll">
        <%@ include file="/WEB-INF/jsp/common/header.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 border-bottom">
                <h1 class="h2">디바이스 관리</h1>
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
                        <button type="button" id="btnStatus" class="btn btn-outline-secondary">삭제</button>
                    </div>
                </div>
            </div>
        </main>
        <!-- <div id="hiddenDivLoading">
            <img src="/img/loading_img.gif" style="margin-left: auto; margin-right: auto; display: block;"/>
        </div> -->
        <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        <script src="/plugins/jqgrid/4.4.3/js/i18n/grid.locale-en.js"></script>
        <script src="/plugins/jqgrid/4.4.3/js/jquery.jqGrid.src.js"></script>
        <script type="text/javascript">

            var controlStatus = "detail";
            $(document).ready(function(){

                // 그리드
                initGrid();

                // 삭제 버튼
                $("#btnStatus").click(function() {
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
                // 등록 버튼
                $("#btnInsert").click(function() {
                   locationUrl("device_view_insert");
                });
            });

            function initGrid() {
                $('#mainGrid').jqGrid({
                    url: '/data/device/page',
                    datatype: "json",
                    height: 225,
                    width: 1100,
                    colNames: ['디바이스 관리 ID', '회사명', '디바이스 모델명', '이동수단 식별번호', '유심 전화번호'],
                    colModel: [
                        {name:'dvcId',              index:'dvcId',          align:'center', hidden:true,    title:false},
                        {name:'cmpnNm',             index:'cmpnNm',         align:'center', width:'25%',    title:false},
                        {name:'dvcMdlNm',           index:'dvcMdlNm',       align:'center', width:'25%',    title:false},
                        {name:'vehicleNum',         index:'vehicleNum',     align:'center', width:'25%',    title:false},
                        {name:'hndsetNum',          index:'hndsetNum',      align:'center', width:'25%',    title:false}
                    ],
                    rowNum : 10,
                    pager: '#pager',
                    jsonReader: {
                         repeatitems:false
                    },
                    onSelectRow: function(rowid, status, e) {
                        if(controlStatus == "detail") {
                            var row = $(this).getRowData(rowid);
                            var moveUrl = function() {
                                return findUrl("device_view_detail") + "?dvcId=" + row.dvcId;
                            }
                            locationUrlHandler(moveUrl);
                        } else if(controlStatus == "delete") {
                            var row = $(this).getRowData(rowid);
                            if(confirm("해당 데이터를 삭제하시겠습니까?")) {
                                fnDelete(row.dvcId);
                            }
                        }
                    },
                    loadui: 'diable'
                });
            }

            function fnDelete(dvcId) {
                fnCommPostCall("device_data_delete", {
                    dvcId: dvcId
                }, function(data) {
                    locationUrl("device_view_list");
                });
            }
        </script>
    </body>
</html>
