<%@ include file="header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2 class="mb-4">Lista de Pessoas</h2>

<c:if test="${not empty sucesso}">
  <div class="alert alert-success" role="alert">
    ${sucesso}
  </div>
</c:if>

<c:if test="${not empty erro}">
  <div class="alert alert-danger" role="alert">
    ${erro}
  </div>
</c:if>

<table class="table table-striped">
  <thead class="table-light">
    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th>Data Nasc.</th>
      <th>CPF</th>
      <th>Funcionário</th>
      <th>Gerente</th>
      <th>Ações</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="p" items="${pessoas}">
      <tr>
        <td>${p.id}</td>
        <td>${p.nome}</td>
        <td><fmt:formatDate value="${p.dataNascimento}" pattern="dd/MM/yyyy"/></td>
        <td>${p.cpf}</td>
        <td><c:out value="${p.funcionario ? 'Sim' : 'Não'}"/></td>
        <td><c:out value="${p.gerente ? 'Sim' : 'Não'}"/></td>
        <td>
          <a href="${pageContext.request.contextPath}/pessoas/editar/${p.id}" 
             class="btn btn-sm btn-primary">Editar</a>
          <form action="${pageContext.request.contextPath}/pessoas/excluir/${p.id}" 
                method="post" style="display:inline;">
            <button type="submit" class="btn btn-sm btn-danger">Excluir</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>


<%@ include file="footer.jspf" %>