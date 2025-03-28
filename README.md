# Rodizio-Organistas-CCB
Sistema para Facilitar a Geração do Rodizio de Organistas da CCB
## Possui:
- Cadastro de Igreja, com os dias de Culto.
- Cadastro de Organistas, com os dias que elas estão disponíveis para tocar.

## Execução:
- Gera o Rodízio para o período informado.
- Escolhe-se o dia e as organistas que tocarão neste dia.
- O sistema traz somente as organistas que tem disponibilidade neste dia da semana.
- O sistema atualiza o contador de vezes que a organista foi escolhida e traz na próxima escolha a que tiver tocado a menor quantidade.

## Como Rodar
### Usando release
#### Requisitos
- JRE 8+
#### Instruções
- Basta realizar o download o pacote pré compilado na sessão de `releases` do github.
- Execute o arquivo baixado com duplo clique ou:
  ```bash
  java -jar rodizio-1.0-SNAPSHOT.jar
  ```
  - Lembre de substituir o `rodizio-1.0-SNAPSHOT.jar` pelo real nome do arquivo baixado.

### Realizando build
#### Requisitos
- JDK 20+
- Maven Apache
#### Instruções
- Clone esse repositório.
- No diretório onde ele foi clonado execute o comando de build:
  ```bash
  mvn clean package
  ```
- Navegue para a pasta `target` e execute o arquivo jar com:
  ```bash
  java -jar rodizio-1.0-SNAPSHOT.jar
  ```
  - O nome pode mudar de acordo com a versão.

