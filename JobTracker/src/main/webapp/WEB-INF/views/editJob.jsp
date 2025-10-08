<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Job</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>

    <!-- Navigation Bar -->
    <nav>
        <div class="container">
            <div class="nav-content">
                <div class="logo">
                    <i data-lucide="briefcase"></i>
                    <span>Job Tracker</span>
                </div>
                <div class="nav-buttons">
                    <button class="nav-btn" onclick="window.location.href='${pageContext.request.contextPath}/home'">
                        <i data-lucide="home"></i> Home
                    </button>
                    <button class="nav-btn active">
                        <i data-lucide="edit"></i> Edit Job
                    </button>

                    <!-- Show Username and Logout If Logged In -->
                    <c:if test="${not empty sessionScope.loggedInUser}">
                        <div class="profile">
                            <i data-lucide="user"></i>
                            <span class="username">${sessionScope.loggedInUser.userName}</span>
                            <button id="logoutBtn" class="nav-btn logout" onclick="window.location.href='${pageContext.request.contextPath}/auth/logout'">
                                <i data-lucide="log-out"></i> Logout
                            </button>
                        </div>
                    </c:if>

                    <!-- Show Login Button If Not Logged In -->
                    <c:if test="${empty sessionScope.loggedInUser}">
                        <button class="nav-btn login" onclick="window.location.href='${pageContext.request.contextPath}/auth/login'">
                            <i data-lucide="log-in"></i> Login
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>

    <main class="container">
        <h2>Edit Job Application</h2>
        <form action="${pageContext.request.contextPath}/jobs/update/${job.id}" method="post" class="job-form">
            <label for="company">Company:</label>
            <input type="text" id="company" name="company" value="${job.company}" required>

            <label for="position">Position:</label>
            <input type="text" id="position" name="position" value="${job.position}" required>

            <label for="status">Status:</label>
            <select id="status" name="status">
                <option value="Applied" ${job.status == 'Applied' ? 'selected' : ''}>Applied</option>
                <option value="Interviewing" ${job.status == 'Interviewing' ? 'selected' : ''}>Interviewing</option>
                <option value="Rejected" ${job.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
            </select>

            <label for="appliedDate">Applied Date:</label>
            <input type="date" id="appliedDate" name="appliedDate" value="${job.appliedDate}" required>

            <button type="submit" class="submit-btn">Update Job</button>
        </form>
    </main>

    <script>
        lucide.createIcons();
    </script>

</body>
</html>
