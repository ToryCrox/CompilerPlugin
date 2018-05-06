package com.hetaozs.tory.processor;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.auto.service.AutoService;
import com.hetaozs.tory.util.Logger;
import com.hetaozs.tory.util.Utils;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import static com.hetaozs.tory.util.Consts.AROUTER_ANNOTATION_TYPE_ROUTE;
import static com.hetaozs.tory.util.Consts.KEY_MODULE_NAME;

@AutoService(Processor.class)
@SupportedOptions(KEY_MODULE_NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({AROUTER_ANNOTATION_TYPE_ROUTE})
public class AutoRouterManifestProcessor extends AbstractProcessor{
    private static final String TAG = "AutoRouterManifestProce";

    private Filer mFiler;
    private Logger logger;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        logger = new Logger(processingEnvironment.getMessager());

        logger.info(TAG + " init...");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (Utils.isNotEmpty(set)) {
            try {
                logger.info(">>> Found autowired field, start... <<<");
                generateManifest(roundEnvironment.getElementsAnnotatedWith(Route.class));

            } catch (Exception e) {
                logger.error(e);
            }
            return true;
        }
        return false;
    }

    private void generateManifest(Set<? extends Element> elements) throws IOException {
        if(Utils.isNotEmpty(elements)){

            for (Element element : elements) {
                Route routeAnnotation = element.getAnnotation(Route.class);
                String annotaClassName = getClassName(element);


                logger.info("element:"+getClassName(element)
                        +", path="+routeAnnotation.path());
                logger.info("CLASS_OUTPUT="+ StandardLocation.CLASS_OUTPUT
                        + ", CLASS_PATH="+ StandardLocation.CLASS_PATH
                        + ", SOURCE_OUTPUT="+ StandardLocation.SOURCE_OUTPUT
                        + ", SOURCE_PATH="+ StandardLocation.SOURCE_PATH
                );

                FileObject existingFile = mFiler.getResource(StandardLocation.SOURCE_OUTPUT, "",
                        "manifest/test");
                logger.info(" existingFile="+existingFile.toUri());
            }

        }

    }


    private String getClassName(Element element){
        return element.getEnclosingElement() + "."+element.getSimpleName();
    }


}
