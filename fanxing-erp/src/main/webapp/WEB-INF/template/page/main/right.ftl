<div class="col-sm-12">
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-6">
			<!-- well -->
		<div class="well">
			<div id="myCarousel-2" class="carousel slide">
				<ol class="carousel-indicators">
					<li data-target="#myCarousel-2" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel-2" data-slide-to="1" class=""></li>
					<li data-target="#myCarousel-2" data-slide-to="2" class=""></li>
				</ol>
				<div class="carousel-inner">
					<!-- Slide 1 -->
					<div class="item active">
						<img src="${resources_static}/style/index/img/demo/m3.jpg" alt="">
						<div class="carousel-caption caption-right">
							<br>
							<a href="javascript:void(0);" class="btn btn-info btn-sm">Read more</a>
						</div>
					</div>
					<!-- Slide 2 -->
					<div class="item">
						<img src="${resources_static}/style/index/img/demo/m1.jpg" alt="">
						<div class="carousel-caption caption-left">
							<br>
							<a href="javascript:void(0);" class="btn btn-danger btn-sm">Read more</a>
						</div>
					</div>
					<!-- Slide 3 -->
					<div class="item">
						<img src="${resources_static}/style/index/img/demo/m2.jpg" alt="">
						<div class="carousel-caption">
							<br>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#myCarousel-2" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span> </a>
				<a class="right carousel-control" href="#myCarousel-2" data-slide="next"> <span class="glyphicon glyphicon-chevron-right"></span> </a>
			</div>

		</div>
		<!-- end well-->
		</div>
	</div>
</div>
<script>
	$('.carousel.slide').carousel({
				interval : 3000,
				cycle : true
			});
</script>
