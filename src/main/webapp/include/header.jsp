<link rel="stylesheet" href="css/shared.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
  $(function() {
    $(".toggle").on("click", function(){
      if($(".item").hasClass("active")){
        $(".item").removeClass("active");
      } else {
        $(".item").addClass("active");
      }
    })
  });

</script>