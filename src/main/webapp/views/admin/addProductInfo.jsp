<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!doctype html>
<html lang="zh-cn">
	<jsp:include page="head.jsp">
		<jsp:param value="鲜出没中心管理系统" name="title"/>
	</jsp:include>
	<body>
		<div class="header">
			<h1>中心管理系统</h1>
		</div>
		<div class="step-progress">
			<ul class="progress-list">
				<li class="long-li active">
					请填写产品信息
				</li>
				<span class="right-angle-active"></span>
				<span class="hollow-angle"></span>
				<li class="long-li"></li>
				<span class="right-angle"></span>
				<span class="hollow-angle"></span>
				<li class="long-li"></li>
			</ul>
		</div>
		<div class="container">
			<div class="content">
				<form role="form">
					<div class="item" id="brand-a">
						<div class="form-group">
							<label for="type" class="col-sm-3">产品名称:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control" />
							</div>
						</div>
	
						<div class="form-group">
							<label for="brand" class="col-sm-3">产品单价:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" />
							</div>
						</div>
	
						<div class="form-group">
							<label for="application" class="col-sm-3">产品规格:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="grade" class="col-sm-3">产地:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="grade" class="col-sm-3">上传产品图:</label>
							<div class="col-sm-6">
								<span class="add-img">+</span>
								<input type="file" id="product" class="img-load" />
							</div>
						</div>
						<div class="form-group">
							<label for="identification" class="col-sm-3">产品介绍:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="grade" class="col-sm-3">上传介绍图:</label>
							<div class="col-sm-6">
								<span class="add-img">+</span>
								<input type="file" id="product-introduction" class="img-load"/>
							</div>
						</div>
						<div class="form-group">
							<label for="pack" class="col-sm-3">产品营养价值:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="grade" class="col-sm-3">上传营养价值图:</label>
							<div class="col-sm-6">
								<span class="add-img">+</span>
								<input type="file" id="product-value" class="img-load"/>
							</div>
						</div>
					</form>
				</div>
			</div>
		<button class="btn btn-info pull-right bottom-margin-2x">下一步</button>
	</body>
</html>