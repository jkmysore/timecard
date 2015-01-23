<div class="pagination" style="clear:both;margin-top: 5px;">								
	<ul class="pagin">
		<c:if test="${page ne 0}">
			<li><a  href="#" onClick="addPageNo(${first});">First</a></li>
			<li>  <a href="#" onClick="addPageNo(${page-1});">Prev</a></li>
		</c:if>
		<c:choose>
			<c:when test="${end eq 1 }"></c:when>
				<c:when test="${end lt 10 }">
					<c:forEach var="i" begin="0" end="${end-1 }">
						<c:choose>
							<c:when test="${page eq i }">
								<li><a class="current" href="#" onClick="addPageNo(${i});">${i+1 }</a></li>
							</c:when>
							<c:otherwise>
								<li><a  href="#" onClick="addPageNo(${i});">${i+1 }</a></li>
							</c:otherwise>
						</c:choose>																	
					</c:forEach>																
				</c:when>
												
				<c:when test="${ page  lt 5}">
					<c:forEach var="i" begin="0" end="9">
						<c:choose>
							<c:when test="${page eq i }">
								<li><a class="current" href="#" onClick="addPageNo(${i});">${i+1 }</a></li>
							</c:when>
							<c:otherwise>
								<li><a  href="#" onClick="addPageNo(${i});">${i+1 }</a></li>
							</c:otherwise>
						</c:choose>																	
					</c:forEach>																
				</c:when>
																			
				<c:when test="${page ge 5 && page+5 le end }">
					<c:forEach var="i" begin="${page-5 }" end="${page+4 }">
						<c:choose>
							<c:when test="${page eq i }">
								<li><a class="current" href="#" onClick="addPageNo(${i});">${i+1 }</a></li>
							</c:when>
							<c:otherwise>
								<li><a  href="#" onClick="addPageNo(${i});">${i+1 }</a></li>
							</c:otherwise>
						</c:choose>																	
					</c:forEach>												
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="${end-10 }" end="${end-1 }">
						<c:choose>
							<c:when test="${page eq i }">
								<li><a class="current" href="#" onClick="addPageNo(${i});">${i+1 }</a></li>
							</c:when>
							<c:otherwise>
								<li><a  href="#" onClick="addPageNo(${i});">${i+1 }</a></li>
							</c:otherwise>
						</c:choose>																	
					</c:forEach>															
			  </c:otherwise>								
	</c:choose>									
	   <c:if test="${page lt end-1 }">
				<li>  <a href="#" onClick="addPageNo(${page+1});">Next</a></li>
	   </c:if>
		<c:if test="${page ne end-1 }">
			<li><a  href="#" onClick="addPageNo(${end-1});">Last</a></li>
		</c:if>
  </ul>
</div>
	<script type="text/javascript">
		function addPageNo(element){
			
			jQuery("input[name='pagerDto.pageNo']").val(element).closest('form').submit();
		}
	</script>
