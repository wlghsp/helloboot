package tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();

        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        return autoConfigs.toArray(new String[0]);

        // list를 String[] 변환하는 방법들 // 아래 코드 가능하나 이해하기 쉽게 위 코드 사용함
        // return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
        // return autoConfigs.stream().toArray(String[]::new);
        // return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new); <-
    }
}
