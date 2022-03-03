
    function fnCommPostCall(urlName, sendData, callback) {
        var url = findUrl(urlName);
        $.ajax({
            url: url,
            type: 'POST',
            //async: false,
            contentType: 'application/json',
            data: JSON.stringify(sendData),
            success: callback,
            error: function(xhr, status) {
                console.log(sendData);
                console.log("call error: " + url);
            }
        });
    };

    function fnCommGetCall(urlHandler, callback) {
        var url = urlHandler();
        $.ajax({
            url: url,
            type: 'GET',
            //async: false,
            contentType: 'application/json',
            success: callback,
            error: function(xhr, status) {
                console.log(sendData);
                console.log("call error: " + url);
            }
        });
    };

    function locationUrlHandler(urlHandler) {
        var url = urlHandler();
        $(location).attr('href', url);
    }

    function locationUrl(urlName) {
        $(location).attr('href', findUrl(urlName));
    }

    function findUrl(urlName) {
        var url = "";
        for(urlKey in urlMapping) {
            if(urlKey == urlName) {
                url = urlMapping[urlKey];
            }
        }
        return url;
    }

    function changeAttrClass(elem, removeClassName, addClassName) {
        $(elem).removeClass(removeClassName);
        $(elem).addClass(addClassName);
    }

    function checkFormValidate(elem, nextAction) {
        if(elem.isValid()) {
            nextAction();
        } else {
            elem.addClass('was-validated');
        }
    }

    function initSelectBox(elem) {
        $(elem).children('option').remove();
        $(elem).append('<option value="">선택</option>');
    }

    function fnElemUnDisabledById(elemIdList) {
        for(i in elemIdList) {
            $("#" + elemIdList[i]).removeAttr("disabled");
        }
    }

    function fnElemDisabledById(elemIdList) {
        for(i in elemIdList) {
            $("#" + elemIdList[i]).attr("disabled",true);
        }
    }

    function LoadingWithMask() {
        //화면의 높이와 너비를 구합니다.
        var maskHeight = $(document).height();
        var maskWidth  = window.document.body.clientWidth;

        //화면에 출력할 마스크를 설정해줍니다.
        var mask       ="<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
        var loadingImg ='';

        loadingImg +="<div id='loadingImg'>";
        loadingImg +=" <img src='/img/loading_img.gif' style='position: relative; display: block; margin: 0px auto;'/>";
        loadingImg +="</div>";

        //화면에 레이어 추가
        $('body')
            .append(mask)
            .append(loadingImg)

        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
        $('#mask').css({
                'width' : maskWidth
                ,'height': maskHeight
                ,'opacity' :'0.3'
        });

        //마스크 표시
        $('#mask').show();

        //로딩중 이미지 표시
        $('#loadingImg').show();
    }

