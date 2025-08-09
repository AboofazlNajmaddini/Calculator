<h1 align="center">Calculator</h1>


<h2>📌 Prerequisites</h2>
<ul>
  <li>JDK 11 or higher (Recommended: JDK 17/21)</li>
  <li>JavaFX SDK (matching your JDK version)</li>
  <li>IntelliJ IDEA (optional)</li>
</ul>

<h2>🚀 Project Setup</h2>

<h3>1. Install JavaFX SDK</h3>
<ol>
  <li>Download the appropriate version from <a href="https://gluonhq.com/products/javafx/">JavaFX official site</a></li>
  <li>Extract to a preferred location (e.g., <code>C:\javafx-sdk-21</code>)</li>
</ol>

<h3>2. Development Environment Configuration</h3>

<h4>For IntelliJ IDEA:</h4>
<pre><code>1. Open the project
2. Go to File > Project Structure > Libraries
3. Click + and add the lib folder from JavaFX SDK
4. Navigate to Run > Edit Configurations
5. Add this to VM Options (adjust the path):
   <b>--module-path "/path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml</b>
</code></pre>

<h3>3. Running the Project</h3>

<h4>Method 1: Using IDE</h4>
<ol>
  <li>Ensure VM Options are properly configured</li>
  <li>Click the Run button</li>
</ol>

<h4>Method 2: Command Line</h4>
<pre><code># Compile
javac --module-path "/path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml -d out src/*.java

# Run
java --module-path "/path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml -cp out MainClass
</code></pre>

<h2>🔧 Troubleshooting</h2>

<table>
  <tr>
    <th>Error</th>
    <th>Solution</th>
  </tr>
  <tr>
    <td>JavaFX runtime components are missing</td>
    <td>Verify VM Options and JavaFX path are correct</td>
  </tr>
  <tr>
    <td>Module javafx.controls not found</td>
    <td>Ensure JavaFX version matches JDK and module path is correct</td>
  </tr>
</table>

<h2>📜 Technical Information</h2>
<ul>
  <li>Required JDK version: 17+</li>
  <li>JavaFX version: 21</li>
  <li>Other dependencies: [List your dependencies]</li>
</ul>
