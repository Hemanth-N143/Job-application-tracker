<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${job.id == null ? "Add Job" : "Edit Job"}</title>

    <!-- ✅ Link CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- ✅ Lucide Icons -->
    <script src="https://unpkg.com/lucide@latest"></script>

    <!-- ✅ JavaScript -->
    <script>
        function cancelForm() {
            window.location.href = "${pageContext.request.contextPath}/home";
        }
    </script>

    <style>
        .hidden { display: none; }
    </style>
</head>
<body>

    <!-- ✅ Job Application Form -->
    <div id="jobFormView">
        <div class="form-container">
            <h2>${job.id == null ? "Add New Job Application" : "Edit Job Application"}</h2>

            <!-- ✅ Form dynamically changes action (Add or Update) -->
            <form id="jobForm"
                  action="${pageContext.request.contextPath}/jobs/${job.id == null ? 'add' : 'update'}"
                  method="post">

                <!-- ✅ Hidden input for ID when updating -->
                <c:if test="${job.id != null}">
                    <input type="hidden" name="id" value="${job.id}">
                </c:if>

                <input type="hidden" name="email" value="${email}">

                <div class="form-group">
                    <label>Company Name</label>
                    <input type="text" name="company" value="${job.company}" placeholder="Enter company name" required>
                </div>

                <div class="form-group">
                    <label>Position</label>
                    <input type="text" name="position" value="${job.position}" placeholder="Enter position" required>
                </div>

                <div class="form-group">
                    <label>Status</label>
                    <select name="status">
                        <option value="applied" ${job.status == 'applied' ? 'selected' : ''}>Applied</option>
                        <option value="interviewing" ${job.status == 'interviewing' ? 'selected' : ''}>Interviewing</option>
                        <option value="rejected" ${job.status == 'rejected' ? 'selected' : ''}>Rejected</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Applied Date</label>
                    <input type="date" name="appliedDate" value="${job.appliedDate}" required>
                </div>

                <div class="form-group">
                    <label>Notes</label>
                    <textarea name="notes" rows="3" placeholder="Enter notes"></textarea>
                </div>

                <div class="form-buttons">
                    <button type="button" class="btn-secondary" onclick="cancelForm()">Cancel</button>
                    <button type="submit" class="btn-primary">${job.id == null ? "Save Application" : "Update Job"}</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
