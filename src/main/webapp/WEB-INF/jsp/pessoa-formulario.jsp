<%@ include file="header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>
  <c:choose>
    <c:when test="${pessoa.id == null}">Nova Pessoa</c:when>
    <c:otherwise>Editar Pessoa</c:otherwise>
  </c:choose>
</h2>

<form action="${pageContext.request.contextPath}/pessoas" method="post">
  <c:if test="${not empty pessoa.id}">
    <input type="hidden" name="id" value="${pessoa.id}" />
  </c:if>

  <!-- Nome -->
  <div class="mb-3">
    <label for="nome">Nome</label>
    <input type="text" id="nome" name="nome"
           value="${pessoa.nome}" class="form-control" required />
  </div>

  <!-- CPF -->
  <div class="mb-3">
    <label for="cpf">CPF</label>
    <input type="text" id="cpf" name="cpf"
           value="${pessoa.cpf}" class="form-control" required />
  </div>

  <!-- Data de Nascimento -->
  <div class="mb-3">
    <label for="dataNascimento">Data de Nascimento</label>
    <input type="date" id="dataNascimento" name="dataNascimento"
           value="${pessoa.dataNascimento}" class="form-control" />
  </div>

  <!-- Funcionário? -->
  <div class="form-check mb-3">
    <input class="form-check-input" type="checkbox"
           id="funcionario" name="funcionario"
           <c:if test="${pessoa.funcionario}">checked</c:if> />
    <label class="form-check-label" for="funcionario">
      É funcionário?
    </label>
  </div>

  <!-- Gerente? -->
  <div class="form-check mb-3">
    <input class="form-check-input" type="checkbox"
           id="gerente" name="gerente"
           <c:if test="${pessoa.gerente}">checked</c:if> />
    <label class="form-check-label" for="gerente">
      É gerente?
    </label>
  </div>

  <button type="submit" class="btn btn-primary">Salvar</button>
  <a href="${pageContext.request.contextPath}/pessoas" class="btn btn-secondary">
    Cancelar
  </a>
</form>

<%@ include file="footer.jspf" %>
