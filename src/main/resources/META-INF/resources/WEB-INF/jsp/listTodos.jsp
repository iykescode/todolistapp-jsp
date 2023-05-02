<%@ include file="common/header.jspf" %>
<%@ include file="common/nav.jspf" %>
    <div class="container">
        <h1>Your Todos: </h1>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>description</th>
                <th>target date</th>
                <th>is done?</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${todos}" var="todo">
                <tr>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <td><a href="/update-todo/${todo.id}" class="btn btn-info">i</a></td>
                    <td><a href="/delete-todo/${todo.id}" class="btn btn-danger">x</a></td>
                </tr>
            </c:forEach>
        </table>

        <a href="/add-todo" class="btn btn-dark">+</a>
    </div>
<%@ include file="common/footer.jspf" %>