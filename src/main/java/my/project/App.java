package my.project;

import static dev.langchain4j.data.document.FileSystemDocumentLoader.loadDocument;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;

import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.ALL_MINILM_L6_V2_EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

  // Please also check ServiceWithRetrieverExample

  public static void main(String[] args) throws Exception {

    EmbeddingModel embeddingModel = new ALL_MINILM_L6_V2_EmbeddingModel();

    EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

    EmbeddingStoreIngestor ingestor =
        EmbeddingStoreIngestor.builder()
            .documentSplitter(DocumentSplitters.recursive(500, new OpenAiTokenizer(GPT_3_5_TURBO)))
            .embeddingModel(embeddingModel)
            .embeddingStore(embeddingStore)
            .build();

    Document document = loadDocument(toPath("example-files/story-about-happy-carrot.txt"));
    ingestor.ingest(document);

    ConversationalRetrievalChain chain =
        ConversationalRetrievalChain.builder()
            .chatLanguageModel(OpenAiChatModel.withApiKey("my-openai-key"))
            .retriever(EmbeddingStoreRetriever.from(embeddingStore, embeddingModel))
            // .chatMemory() // you can override default chat memory
            // .promptTemplate() // you can override default prompt template
            .build();

    String answer = chain.execute("Who is Charlie?");
    System.out.println(answer); // Charlie is a cheerful carrot living in VeggieVille...
  }

  private static Path toPath(String fileName) {
    try {
      URL fileUrl = App.class.getResource(fileName);
      return Paths.get(fileUrl.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
