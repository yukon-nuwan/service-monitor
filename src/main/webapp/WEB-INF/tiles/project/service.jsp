
<div id="main-content" class="container">
	<div class="container">
	    <div class="row commonDivMargin">
	        <div class="col-md-10 mx-auto">
	            <form>
	            	<div id="serviceDiv">
						<input type="hidden" id="id" name="id">
		                <div class="form-group row">
		                    <div class="col-sm-6">
		                        <label for="host">Host</label>
		                        <input type="text" class="form-control" id="host" name="host" placeholder="Host">
		                    </div>
		                    <div class="col-sm-6">
		                        <label for="port">Port</label>
		                        <input type="text" class="form-control" id="port" name="port" placeholder="Port">
		                    </div>
		                </div>
		                <div class="form-group row">
		                    <div class="col-sm-6">
		                        <label for="outageStart">Outage Start (HH:mm)</label>
		                        <input type="text" class="form-control" id="outageStart" name="outageStart" placeholder="00:00">
		                    </div>
		                    <div class="col-sm-6">
		                        <label for="outageEnd">Outage End (HH:mm)</label>
		                        <input type="text" class="form-control" id="outageEnd" name="outageEnd" placeholder="00:00">
		                    </div>
		                </div>
		                <button type="button" id="delete" onclick="deleteService()" disabled="disabled"  class="btn btn-primary px-4 float-right btn-gap-yukon">Delete</button>
		                <button type="button" id="update" onclick="updateService()" disabled="disabled"  class="btn btn-primary px-4 float-right btn-gap-yukon">Update</button>
		                <button type="button" id="save" onclick="saveService()"  class="btn btn-primary px-4 float-right btn-gap-yukon">Save</button>
		       		</div>         
	            </form>
	        </div>
	    </div>
	     <div class="row">
	        <div class="col-md-12">
	            <table id="conversation" class="table table-striped table-hover">
	                <thead>
	                <tr>
	                    <th>Host</th>
	                    <th>Port</th>
	                    <th>Outage Start</th>
	                    <th>Outage End</th>
	                </tr>
	                </thead>
	                <tbody id="serviceTableBody">
	                </tbody>
	            </table>
	        </div>
	    </div>
	</div> 
</div>
<script type="text/javascript" src="resources/js/project/service.js"></script>
