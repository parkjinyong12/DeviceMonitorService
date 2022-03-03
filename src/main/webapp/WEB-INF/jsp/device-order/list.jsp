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
                <h1 class="h2">디바이스 선택</h1>
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
            </div>
        </main>
        <!-- <div id="hiddenDivLoading">
            <img src="/img/loading_img.gif" style="margin-left: auto; margin-right: auto; display: block;"/>
        </div> -->
        <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
        <script src="/plugins/jqgrid/4.4.3/js/i18n/grid.locale-en.js"></script>
        <script src="/plugins/jqgrid/4.4.3/js/jquery.jqGrid.src.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){

                // 그리드
                initGrid();

                // 등록 버튼
                $("#btnInsert").click(function() {
                   locationUrl("device_stock_view_insert");
                });
            });

            function initGrid() {
                $('#mainGrid').jqGrid({
                    url: '/data/device-stock/page/device-model',
                    datatype: "json",
                    height: 225,
                    width: 1100,
                    colNames: ['디바이스 모델 ID', '디바이스 모델명', '디바이스 재고량'],
                    colModel: [
                        {name:'dvcMdlId',       index:'dvcMdlId',          align:'center', hidden:true,    title:false},
                        {name:'dvcMdlNm',       index:'dvcMdlNm',         align:'center', width:'50%',    title:false},
                        {name:'dvcMdlCnt',          index:'dvcMdlCnt',       align:'center', width:'50%',    title:false}
                    ],
                    rowNum : 10,
                    pager: '#pager',
                    jsonReader: {
                         repeatitems:false
                    },
                    onSelectRow: function(rowid, status, e) {
                        var row = $(this).getRowData(rowid);
                        var moveUrl = function() {
                            return findUrl("device_stock_view_detail") + "?dvcMdlId=" + row.dvcMdlId;
                        }
                        locationUrlHandler(moveUrl);
                    },
                    loadui: 'diable'
                });
            }
        </script>
    </body>
</html>
