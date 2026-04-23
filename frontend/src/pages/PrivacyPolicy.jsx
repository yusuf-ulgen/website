import React, { useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import myLogo from '../assets/website_logo.png';

const privacyData = {
  'perfect-bridge': {
    title: 'Perfect Bridge',
    date: 'April 23, 2026',
    content: `
      <p>This Privacy Policy describes how <strong>Yusuf Ülgen</strong> ("we," "us," or "our") handles information when you use the <strong>Perfect Bridge</strong> mobile application (the "App").</p>
      
      <h2>1. Information We Collect</h2>
      <h3>a. Personal Information</h3>
      <p>We <strong>do not collect any personal information</strong> that can identify you directly, such as your name, email address, physical address, or phone number. You can use the App without creating an account or providing any personal details.</p>
      
      <h3>b. Non-Personal Information</h3>
      <p>The App stores certain gameplay data locally on your device:</p>
      <ul>
        <li><strong>High Scores:</strong> Your best scores are saved on your device using local storage to track your progress. This data stays on your device and is not transmitted to our servers.</li>
      </ul>
      
      <h2>2. Third-Party Services</h2>
      <p>We may use third-party services that collect information used to identify you. Below is a list of the third-party service providers used by the App (if applicable):</p>
      <ul>
        <li><strong>Google Play Services:</strong> Used for app functionality and potentially for Leaderboards/Achievements if you choose to sign in. Please refer to <a href="https://policies.google.com/privacy" target="_blank" rel="noopener noreferrer">Google's Privacy Policy</a>.</li>
      </ul>
      <p><em>(Note: Currently, this app does not include advertisements or external analytics SDKs. If this changes in the future, this policy will be updated.)</em></p>
      
      <h2>3. Log Data</h2>
      <p>We want to inform you that whenever you use our Service, in a case of an error in the app we may collect data and information (through third-party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing our Service, the time and date of your use of the Service, and other statistics.</p>
      
      <h2>4. Cookies</h2>
      <p>Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory. This Service does not use these “cookies” explicitly.</p>
      
      <h2>5. Security</h2>
      <p>We value your trust in providing us your information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and we cannot guarantee its absolute security.</p>
      
      <h2>6. Children’s Privacy</h2>
      <p>These Services do not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. In the case we discover that a child under 13 has provided us with personal information, we immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact us so that we will be able to do necessary actions.</p>
      
      <h2>7. Changes to This Privacy Policy</h2>
      <p>We may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. We will notify you of any changes by posting the new Privacy Policy on this page.</p>
      
      <h2>8. Contact Us</h2>
      <p>If you have any questions or suggestions regarding this Privacy Policy, please do not hesitate to contact us:</p>
      <p><strong>Website:</strong> <a href="https://yusufulgen.com" target="_blank">yusufulgen.com</a><br/>
      <strong>Email:</strong> <a href="mailto:yusufulgenbusiness@gmail.com">yusufulgenbusiness@gmail.com</a></p>
    `
  },
  'bold-solter': {
    title: 'Bold Solter',
    date: 'April 23, 2026',
    content: `
      <p>Thank you for using the <strong>Bold Solter</strong> mobile application ("App") developed by Yusuf Ülgen ("we," "us," or "the Developer"). Your privacy is important to us. This Privacy Policy is designed to inform you about how your information is collected, used, and protected when you use the App.</p>
      
      <h2>1. Information Collected</h2>
      <p>The App does not collect directly personally identifiable information (name, surname, email address, etc.). However, some anonymous data may be collected to improve the App experience and serve advertisements:</p>
      <ul>
        <li><strong>Device Information:</strong> Device model, operating system version, and unique device identifiers.</li>
        <li><strong>Usage Data:</strong> Your interactions within the App, game scores, and app usage durations.</li>
      </ul>
      
      <h2>2. Third-Party Services</h2>
      <p>The App may use third-party services that can collect data. Their own privacy policies apply to these services:</p>
      <ul>
        <li><a href="https://www.google.com/policies/privacy/" target="_blank">Google Play Services</a></li>
        <li><a href="https://support.google.com/admob/answer/6128543?hl=en" target="_blank">AdMob (Google)</a>: Used for advertisement delivery.</li>
        <li><a href="https://expo.dev/privacy" target="_blank">Expo</a>: Used for the App infrastructure.</li>
      </ul>
      
      <h2>3. Cookies</h2>
      <p>This App does not explicitly use "cookies." However, third-party codes and libraries used by the App may use "cookies" to collect information and improve their services.</p>
      
      <h2>4. Data Security</h2>
      <p>We use commercially acceptable methods to protect the information you provide to us. However, please remember that no method of transmission over the internet or method of electronic storage is 100% secure, and we cannot guarantee its absolute security.</p>
      
      <h2>5. Children's Privacy</h2>
      <p>These services do not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. If we discover that a child has provided us with personal information, we delete it immediately from our servers.</p>
      
      <h2>6. Changes to This Privacy Policy</h2>
      <p>We may update our Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page. Changes take effect as soon as they are posted on this page.</p>
      
      <h2>7. Contact Us</h2>
      <p>If you have any questions or suggestions regarding this Privacy Policy, please do not hesitate to contact us:</p>
      <p><strong>Website:</strong> <a href="https://yusufulgen.com" target="_blank">yusufulgen.com</a><br/>
      <strong>Email:</strong> <a href="mailto:yusufulgenbusiness@gmail.com">yusufulgenbusiness@gmail.com</a></p>
    `
  },
  'math-orbit-hero': {
    title: 'Math Orbit Hero',
    date: 'April 23, 2026',
    content: `
      <p>Yusuf Ülgen built the Math Orbit Hero app as a Free app. This SERVICE is provided by Yusuf Ülgen at no cost and is intended for use as is.</p>
      <p>This page is used to inform visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service.</p>
      <p>If you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information with anyone except as described in this Privacy Policy.</p>
      
      <h2>Information Collection and Use</h2>
      <p>For a better experience, while using our Service, I may require you to provide us with certain personally identifiable information. The information that I request will be retained on your device and is not collected by me in any way.</p>
      <p>The app does use third-party services that may collect information used to identify you.</p>
      <p>Link to the privacy policy of third-party service providers used by the app:</p>
      <ul>
        <li><a href="https://www.google.com/policies/privacy/" target="_blank">Google Play Services</a></li>
        <li><a href="https://support.google.com/admob/answer/6128543?hl=en" target="_blank">AdMob</a></li>
        <li><a href="https://firebase.google.com/policies/analytics" target="_blank">Google Analytics for Firebase</a></li>
        <li><a href="https://firebase.google.com/support/privacy/" target="_blank">Firebase Crashlytics</a></li>
      </ul>
      
      <h2>Log Data</h2>
      <p>I want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third-party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics.</p>
      
      <h2>Cookies</h2>
      <p>Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory.</p>
      <p>This Service does not use these “cookies” explicitly. However, the app may use third-party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service.</p>
      
      <h2>Service Providers</h2>
      <p>I may employ third-party companies and individuals due to the following reasons:</p>
      <ul>
        <li>To facilitate our Service;</li>
        <li>To provide the Service on our behalf;</li>
        <li>To perform Service-related services; or</li>
        <li>To assist us in analyzing how our Service is used.</li>
      </ul>
      <p>I want to inform users of this Service that these third parties have access to your Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose.</p>
      
      <h2>Security</h2>
      <p>I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security.</p>
      
      <h2>Links to Other Sites</h2>
      <p>This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me. Therefore, I strongly advise you to review the Privacy Policy of these websites. I have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.</p>
      
      <h2>Children’s Privacy</h2>
      <p>These Services do not address anyone under the age of 13. I do not knowingly collect personally identifiable information from children under 13 years of age. In the case I discover that a child under 13 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to do the necessary actions.</p>
      
      <h2>Changes to This Privacy Policy</h2>
      <p>I may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Privacy Policy on this page.</p>
      
      <p>This policy is effective as of 2026-04-23</p>
      
      <h2>Contact Us</h2>
      <p>If you have any questions or suggestions regarding this Privacy Policy, please do not hesitate to contact us:</p>
      <p><strong>Website:</strong> <a href="https://yusufulgen.com" target="_blank">yusufulgen.com</a><br/>
      <strong>Email:</strong> <a href="mailto:yusufulgenbusiness@gmail.com">yusufulgenbusiness@gmail.com</a></p>
    `
  },
  'math-tower': {
    title: 'Math Tower',
    date: 'April 23, 2026',
    content: `
      <p><strong>Math Tower</strong> ("we," "our," or "the App") values the privacy of our users ("you," "the user"). This Privacy Policy explains how your information is collected, used, and protected when you use our mobile application.</p>
      
      <h2>1. Information Collected</h2>
      <h3>Non-Personal Information</h3>
      <p>Our App does not collect any personal data that directly identifies you (name, email address, phone number, etc.). However, anonymous technical data such as the following may be automatically collected to improve the performance of the App and troubleshoot errors:</p>
      <ul>
        <li>Device type and model</li>
        <li>Operating system version</li>
        <li>In-app interactions (scores, level progress, etc.)</li>
      </ul>
      
      <h2>2. Use of Information</h2>
      <p>The anonymous data collected is used solely for the following purposes:</p>
      <ul>
        <li>To analyze and improve the performance of the App.</li>
        <li>To optimize the user experience.</li>
        <li>To detect and resolve technical errors.</li>
      </ul>
      
      <h2>3. Third-Party Services</h2>
      <p>Our App may use some third-party services to provide in-app functionality. These services have their own privacy policies:</p>
      <ul>
        <li><strong>Google Play Services</strong> (for Android)</li>
        <li><strong>Expo</strong> (for the App infrastructure)</li>
      </ul>
      
      <h2>4. Children's Privacy (COPPA)</h2>
      <p>Math Tower takes children's privacy seriously. Our App does not knowingly collect personal information from children under the age of 13. If you as a parent notice that your child has provided us with information, please contact us; we will immediately delete this information from our system.</p>
      
      <h2>5. Data Security</h2>
      <p>We take reasonable administrative and technical measures to ensure the security of your data. However, we cannot guarantee that any data transmitted over the internet is 100% secure.</p>
      
      <h2>6. Changes</h2>
      <p>This Privacy Policy may be updated from time to time. In case of any changes, we will notify you by posting the new policy on this page.</p>
      
      <h2>7. Contact Us</h2>
      <p>If you have any questions or suggestions regarding this Privacy Policy, please do not hesitate to contact us:</p>
      <p><strong>Website:</strong> <a href="https://yusufulgen.com" target="_blank">yusufulgen.com</a><br/>
      <strong>Email:</strong> <a href="mailto:yusufulgenbusiness@gmail.com">yusufulgenbusiness@gmail.com</a></p>
    `
  },
  'todolist': {
    title: 'ToDoList',
    date: 'April 23, 2026',
    content: `
      <p>This Privacy Policy explains how <strong>Yusuf Ülgen</strong> ("we," "us," or "the Developer") collects, uses, and protects your data through the <strong>ToDoList</strong> mobile application ("App").</p>
      
      <h2>1. Information Collected</h2>
      <h3>a. Personal Information</h3>
      <p>To use the App with its full functionality, we may collect the following personal information:</p>
      <ul>
        <li><strong>Google Account Information:</strong> When you choose to sign in with Google, we access basic account information such as your name, email address, and profile picture.</li>
        <li><strong>User Content:</strong> Tasks, notes, and lists you create within the App are securely stored on our servers (Firebase).</li>
      </ul>
      
      <h3>b. Automatically Collected Data</h3>
      <p>The App may collect anonymous technical data to troubleshoot errors and improve the user experience:</p>
      <ul>
        <li>Device model and operating system version.</li>
        <li>App usage statistics and interactions.</li>
        <li>Advertising ID - may be used for analytics and app performance tracking.</li>
      </ul>
      
      <h2>2. Third-Party Services</h2>
      <p>The App uses the following third-party services that may process your data:</p>
      <ul>
        <li><strong>Google Play Services:</strong> Required for core app functions.</li>
        <li><strong>Firebase (Google):</strong>
          <ul>
            <li><strong>Firebase Authentication:</strong> For user sign-in and authentication.</li>
            <li><strong>Cloud Firestore:</strong> For cloud storage and synchronization of your tasks across devices.</li>
            <li><strong>Firebase Cloud Messaging (FCM):</strong> To send task reminders and notifications.</li>
            <li><strong>Firebase Crashlytics:</strong> To report app crashes and improve stability.</li>
          </ul>
        </li>
      </ul>
      
      <h2>3. App Permissions</h2>
      <p>ToDoList requires the following permissions on your device to function correctly:</p>
      <ul>
        <li><strong>Internet Access:</strong> To synchronize data with Firebase.</li>
        <li><strong>Notification Permission:</strong> To receive task reminders.</li>
        <li><strong>Alarms and Reminders:</strong> To send timely notifications for your tasks.</li>
        <li><strong>Run at Startup:</strong> To automatically re-establish reminders when your device is restarted.</li>
      </ul>
      
      <h2>4. Data Security</h2>
      <p>Your data is stored encrypted on Google's secure infrastructure (Firebase). However, we cannot guarantee that any data transmitted over the internet is 100% secure. We use commercially acceptable methods to protect your information.</p>
      
      <h2>5. Children's Privacy</h2>
      <p>Our services do not address children under the age of 13. We do not knowingly collect personal data from children under 13. If you notice that a child has provided us with information, please contact us; we will delete this information immediately.</p>
      
      <h2>6. Contact Us</h2>
      <p>If you have any questions or suggestions regarding this Privacy Policy, please do not hesitate to contact us:</p>
      <p><strong>Website:</strong> <a href="https://yusufulgen.com" target="_blank">yusufulgen.com</a><br/>
      <strong>Email:</strong> <a href="mailto:yusufulgenbusiness@gmail.com">yusufulgenbusiness@gmail.com</a></p>
    `
  },
  'filmlist': {
    title: 'FilmList',
    date: 'April 23, 2026',
    content: `
      <p>This Privacy Policy explains how <strong>Yusuf Ülgen</strong> ("we," "us," or "the Developer") collects, uses, and protects your data through the <strong>FilmList</strong> mobile application ("App").</p>
      
      <h2>1. Information Collected</h2>
      <h3>a. Search and Interaction Data</h3>
      <p>The App collects information about the movies you search for and the content you add to your watchlist to provide a personalized experience:</p>
      <ul>
        <li><strong>Watchlist:</strong> Movies you save are stored on your device using a local database (Room).</li>
        <li><strong>AI Chat Interactions:</strong> When you use the AI chat feature, your queries are processed to provide movie recommendations. These interactions are securely transmitted and processed.</li>
      </ul>
      
      <h2>2. Third-Party Services and APIs</h2>
      <p>The App integrates various APIs to provide movie information and AI features:</p>
      <ul>
        <li><strong>TMDB API:</strong> We use The Movie Database (TMDB) API to get movie details, posters, and ratings. <a href="https://www.themoviedb.org/privacy-policy" target="_blank">TMDB Privacy Policy</a></li>
        <li><strong>Google AI (Gemini):</strong> We use Google's Gemini API to provide smart movie recommendations and chat features. <a href="https://policies.google.com/privacy" target="_blank">Google Privacy Policy</a></li>
        <li><strong>Google Play Services:</strong> Required for core app functionality on Android devices.</li>
      </ul>
      
      <h2>3. App Permissions</h2>
      <p>The App requires the following permissions to function correctly:</p>
      <ul>
        <li><strong>Internet Access:</strong> To pull movie data from TMDB and communicate with Gemini AI.</li>
        <li><strong>Network State Access:</strong> To check if the device is connected to the internet.</li>
      </ul>
      
      <h2>4. Data Retention</h2>
      <p>Most of your data (like your watchlist) is stored directly on your device locally. We do not store your personal movie preferences on our own servers.</p>
      
      <h2>5. Children's Privacy</h2>
      <p>The App does not knowingly collect personal information from children under the age of 13. it addresses a general audience.</p>
      
      <h2>6. Contact Us</h2>
      <p>If you have any questions or suggestions regarding this Privacy Policy, please do not hesitate to contact us:</p>
      <p><strong>Website:</strong> <a href="https://yusufulgen.com" target="_blank">yusufulgen.com</a><br/>
      <strong>Email:</strong> <a href="mailto:yusufulgenbusiness@gmail.com">yusufulgenbusiness@gmail.com</a></p>
    `
  }
};

const PrivacyPolicy = ({ appKey }) => {
  const data = privacyData[appKey] || { title: 'Unknown App', content: 'Policy not found.', date: '' };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <div className="min-h-screen bg-[#0B0616] text-[#E2DCE7] selection:bg-[#d946ef] selection:text-white font-sans relative overflow-x-hidden">
      {/* Background decoration */}
      <div className="absolute top-[-10%] left-[20%] w-[60%] h-[80%] bg-[conic-gradient(from_0deg,transparent_0_180deg,#d946ef_270deg,#8b5cf6_360deg)] rounded-full mix-blend-screen filter blur-[150px] opacity-[0.12] animate-[spin_20s_linear_infinite] pointer-events-none"></div>

      {/* Header */}
      <header className="fixed top-0 left-0 right-0 z-50 flex justify-center pt-2">
        <nav className="w-[90%] max-w-[850px] px-8 py-3.5 mt-4 rounded-full bg-[#0A0510]/95 backdrop-blur-xl border border-white/5 flex items-center justify-between">
          <Link to="/" className="shrink-0 cursor-pointer flex items-center">
            <img src={myLogo} alt="Logo" className="h-8 md:h-10 object-contain mix-blend-screen" />
          </Link>
          <Link to="/" className="text-sm font-medium text-[#8c8496] hover:text-white transition-colors">
            ← Ana Sayfa
          </Link>
        </nav>
      </header>

      {/* Content */}
      <main className="relative z-10 max-w-4xl mx-auto px-6 pt-40 pb-24">
        <div className="bg-white/[0.02] border border-white/5 p-8 md:p-16 rounded-3xl backdrop-blur-md shadow-2xl">
          <h1 className="text-4xl md:text-6xl font-black tracking-tight mb-4 text-[#F8F7F9] bg-clip-text text-transparent bg-gradient-to-r from-white to-[#8c8496]">
            {data.title}
          </h1>
          <div className="flex items-center gap-2 mb-12">
            <span className="h-[1px] w-8 bg-[#8b5cf6]"></span>
            <p className="text-[#8b5cf6] font-mono text-xs tracking-widest uppercase italic font-bold">
              Last Updated: {data.date}
            </p>
          </div>
          
          <div className="privacy-content text-[#928b9c] leading-relaxed space-y-6"
            dangerouslySetInnerHTML={{ __html: data.content }}
          />
        </div>
      </main>

      <style dangerouslySetInnerHTML={{ __html: `
        .privacy-content h2 {
          font-size: 1.875rem;
          font-weight: 800;
          color: #F8F7F9;
          margin-top: 3rem;
          margin-bottom: 1.5rem;
          letter-spacing: -0.025em;
        }
        .privacy-content h3 {
          font-size: 1.25rem;
          font-weight: 700;
          color: #E2DCE7;
          margin-top: 2rem;
          margin-bottom: 1rem;
        }
        .privacy-content p {
          margin-bottom: 1.5rem;
        }
        .privacy-content ul {
          list-style-type: disc;
          padding-left: 1.5rem;
          margin-bottom: 1.5rem;
          color: #928b9c;
        }
        .privacy-content li {
          margin-bottom: 0.5rem;
        }
        .privacy-content a {
          color: #8b5cf6;
          text-decoration: underline;
          transition: color 0.2s;
        }
        .privacy-content a:hover {
          color: #d946ef;
        }
      `}} />

      {/* Footer */}
      <footer className="relative z-10 max-w-7xl mx-auto px-6 py-12 flex flex-col items-center text-center">
        <div className="text-[#50455e] text-[10px] tracking-widest font-mono italic uppercase">
          © {new Date().getFullYear()} YUSUF ÜLGEN. ALL RIGHTS RESERVED.
        </div>
      </footer>
    </div>
  );
};

export default PrivacyPolicy;
