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
                <h1 class="h2">데이터 유심</h1>
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
            <!-- <footer class="my-5 pt-5 text-muted text-center text-small">
                <p class="mb-1">&copy; 2021 Ruokit.com</p>
                <ul class="list-inline">
                    <li class="list-inline-item"><a href="#">Privacy</a></li>
                    <li class="list-inline-item"><a href="#">Terms</a></li>
                    <li class="list-inline-item"><a href="#">Support</a></li>
                </ul>
            </footer> -->
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
                    url: '/data/hndset/page',
                    datatype: "json",
                    height: 225,
                    width: 1100,
                    colNames: ['데이터유심 id', '회사명', '데이터 제공사', '유심 전화번호', '최근 상태'],
                    colModel: [
                        {name:'hndsetId',               index:'hndsetId',               align:'center', hidden:true,    title:false},
                        {name:'cmpnNm',                 index:'cmpnNm',                 align:'center', width:'25%',    title:false},
                        {name:'hndsetCarrierNm',        index:'hndsetCarrierNm',        align:'center', width:'25%',    title:false},
                        {name:'hndsetNum',              index:'hndsetNum',              align:'center', width:'25%',    title:false},
                        {name:'hndsetOpenStNm',         index:'hndsetOpenStNm',         align:'center', width:'25%',    title:false}
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
                                return findUrl("hndset_view_detail") + "?hndsetId=" + row.hndsetId;
                            }
                            locationUrlHandler(moveUrl);
                        } else if(controlStatus == "delete") {
                            var row = $(this).getRowData(rowid);
                            if(confirm("해당 데이터를 삭제하시겠습니까?")) {
                                fnDelete(row.hndsetId);
                            }
                        }
                    }
                });
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

                $("#btnInsert").click(function() {
                   locationUrl("hndset_view_insert");
                });
            });

            function fnDelete(hndsetId) {
                fnCommPostCall("hndset_data_delete", {
                    hndsetId: hndsetId
                }, function(data) {
                    locationUrl("hndset_view_list");
                });
            }
        </script>
    </body>
</html>
