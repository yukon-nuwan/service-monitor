<link rel="stylesheet" href="resources/css/project/caller.css">
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-8">
        	<div id="callerDiv">
	            <form class="form-inline">
            		<input type="hidden" id="id" name="id">
	                <div class="form-group">
					  <label for="name" class="commonMarginRight">Name:  </label>
					  <input type="text" class="form-control commonMarginRight" id="name" name="name">
					</div>
					<button type="button" onclick="save()" class="btn btn-primary">Add</button>
	            </form>
			</div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Name</th>
                </tr>
                </thead>
                <tbody id="callerTableBody">
                </tbody>
            </table>
        </div>
    </div>
</div>


<script src="resources/js/project/caller.js"></script>
