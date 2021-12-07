package dk.stonemountain.demo.demofx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.stonemountain.demo.demofx.installer.PackageInstaller;
import dk.stonemountain.demo.demofx.installer.VersionInformation;
import dk.stonemountain.demo.demofx.util.time.TimeConverter;

public class ApplicationContainer {	
	private static final Logger log = LoggerFactory.getLogger(ApplicationContainer.class);	
	private static ApplicationContainer instance = new ApplicationContainer();

	private final UserPreferences userPreferences = new UserPreferences();
	private final VersionInformation version = new VersionInformation();
	private final PackageInstaller installer = new PackageInstaller();
	private final Backend currentBackend = Backend.PRODUCTION;

	public static ApplicationContainer getInstance() {
		return instance;
	}
	
	private ApplicationContainer() {
		// to prevent instantiation)
		log.info("Starting application container");
		installer.startInstaller();
	}

	public Backend getCurrentBackend() {
		return currentBackend;
	}

	public UserPreferences getUserPreferences() {
		return userPreferences;
	}

	public VersionInformation getVersion() {
		return version;
	}

	public void updateVersion(dk.stonemountain.demo.demofx.installer.backend.VersionInformation info) {
		version.setMustBeUpdated(info.currentIsWorking);
		version.setNewSha(info.recommendedSha);
		version.setNewVersion(info.recommendedVersion);
		version.setNewerVersionAvailable(info.mustBeUpdated);
		version.setNewReleaseNote(info.recommendedReleaseNote);
		version.setNewReleaseTime(TimeConverter.toLocalDateTime(info.recommendedReleaseTime));
	}
}
