<script src="/plugins/jquery/js/jquery-1.12.4.min.js"></script>
<script src="/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/js/custom/feather.min.js"></script>
<script src="/js/custom/Chart.min.js"></script>
<script src="/js/custom/dashboard.js"></script>
<script src="/js/common/const.js"></script>
<script src="/js/common/util.js" charset='utf-8'></script>
<script type="text/javascript">
    // javascript checkValidity function
    $.fn.isValid = function(){
      return this[0].checkValidity()
    }
    $('a[service-view-name="${serviceViewName}"]').addClass("active");
</script>