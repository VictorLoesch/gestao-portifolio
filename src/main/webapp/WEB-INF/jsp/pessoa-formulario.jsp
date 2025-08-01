<%@ include file="header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:choose>
  <c:when test="${pessoa.id == null}">
    <!-- Se não há ID, mostramos só a mensagem -->
    <p class="alert alert-info">
      Criação de nova pessoa somente via API REST:  
      <code>POST /api/pessoas</code>
    </p>
  </c:when>
  <c:otherwise>
    <!-- Se há ID, mostramos o formulário de edição -->
    <h2>Editar Pessoa</h2>
    <form:form modelAttribute="pessoa" action="${pageContext.request.contextPath}/pessoas" method="post">
      <form:hidden path="id"/>

      <div class="mb-3">
        <label for="nome">Nome</label>
        <form:input path="nome" cssClass="form-control" id="nome"/>
        <form:errors path="nome" cssClass="text-danger"/>
      </div>

      <div class="mb-3">
        <label for="cpf">CPF</label>
        <form:input path="cpf" cssClass="form-control" id="cpf"/>
      </div>

      <div class="mb-3">
        <label for="dataNascimento">Data de Nascimento</label>
        <form:input path="dataNascimento" cssClass="form-control" type="date" id="dataNascimento"/>
      </div>

      <div class="form-check mb-3">
        <form:checkbox path="funcionario" cssClass="form-check-input" id="funcionario"/>
        <label class="form-check-label" for="funcionario">É funcionário?</label>
      </div>

      <div class="form-check mb-3">
        <form:checkbox path="gerente" cssClass="form-check-input" id="gerente"/>
        <label class="form-check-label" for="gerente">É gerente?</label>
      </div>

      <button type="submit" class="btn btn-primary">Salvar</button>
      <a href="${pageContext.request.contextPath}/pessoas" class="btn btn-secondary">Cancelar</a>
    </form:form>
  </c:otherwise>
</c:choose>

<%@ include file="footer.jspf" %>
