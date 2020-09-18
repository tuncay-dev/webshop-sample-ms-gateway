package dk.tuncay.webshop;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("dk.tuncay.webshop");

        noClasses()
            .that()
            .resideInAnyPackage("dk.tuncay.webshop.service..")
            .or()
            .resideInAnyPackage("dk.tuncay.webshop.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..dk.tuncay.webshop.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
