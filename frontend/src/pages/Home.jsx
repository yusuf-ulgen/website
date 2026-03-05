import React, { useState, useEffect } from 'react';
import myLogo from '../assets/logo.png';

// --- ÇEVİRİ SÖZLÜĞÜ (SABİT METİNLER) ---
const translations = {
  TR: {
    nav: { about: "Hakkımda", skills: "Yetenekler", projects: "Projeler", contact: "İletişim ↗" },
    hero: { explore: "Projelerimi Keşfet", subtitle: "Dijital dünyada iz bırakan çözümler." },
    about: {
      tag: "Hakkımda",
      title: "Mantık ve Estetiğin Mükemmel Uyumu.",
      archTitle: "Sistem Mimarisi",
      archDesc: "Güvenli ve ölçeklenebilir altyapılar.",
      uiTitle: "UI/UX Tasarım",
      uiDesc: "Ziyaretçiyi müşteriye dönüştüren etkileşimler."
    },
    skills: { tag: "Yetenekler", title: "Teknoloji Yığınım.", noData: "[Henüz veri yok]" },
    projects: { title: "Öne Çıkan İşlerim.", inspect: "Detayları İncele →" },
    contact: {
      tag: "İletişim",
      title1: "Bir fikriniz mi var?",
      title2: "Birlikte inşa edelim.",
      desc: "Yeni bir proje başlatmak için doğrudan ulaşabilirsiniz.",
      btn: "Bize Ulaşın",
      rights: "TÜM HAKLARI SAKLIDIR."
    },
    modal: { devTime: "Geliştirme Süresi:", liveSite: "Canlı Site ↗", github: "GitHub ↗" }
  },
  EN: {
    nav: { about: "About", skills: "Skills", projects: "Projects", contact: "Contact ↗" },
    hero: { explore: "Explore My Work", subtitle: "Solutions that leave a mark in the digital world." },
    about: {
      tag: "About Me",
      title: "The Perfect Harmony of Logic and Aesthetics.",
      archTitle: "System Architecture",
      archDesc: "Secure, scalable, and robust infrastructures.",
      uiTitle: "UI/UX Design",
      uiDesc: "Interactions that turn visitors into customers."
    },
    skills: { tag: "Skills", title: "My Tech Stack.", noData: "[No data yet]" },
    projects: { title: "Featured Work.", inspect: "View Details →" },
    contact: {
      tag: "Contact",
      title1: "Have an idea?",
      title2: "Let's build it together.",
      desc: "You can reach out directly to start a new project.",
      btn: "Contact Us",
      rights: "ALL RIGHTS RESERVED."
    },
    modal: { devTime: "Development Time:", liveSite: "Live Site ↗", github: "GitHub ↗" }
  }
};

function Home() {
  const [isScrolled, setIsScrolled] = useState(false);
  const [selectedProject, setSelectedProject] = useState(null);
  
  const [lang, setLang] = useState('TR');
  const t = translations[lang]; 

  const [projects, setProjects] = useState([]);
  const [skills, setSkills] = useState([]);
  const [profile, setProfile] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  
  useEffect(() => {
    // 1. Sayfa yüklendiğinde anında en üste kaydır
    window.scrollTo(0, 0);
    
    // 2. Eğer URL'de #projects gibi bir uzantı kaldıysa onu temizle ki tarayıcı aşağı atlamasın
    if (window.location.hash) {
      window.history.replaceState(null, '', window.location.pathname);
    }
  }, []);

  useEffect(() => {
    const handleScroll = () => setIsScrolled(window.scrollY > 50);
    window.addEventListener('scroll', handleScroll);
    document.body.style.overflow = selectedProject ? 'hidden' : 'auto';
    return () => window.removeEventListener('scroll', handleScroll);
  }, [selectedProject]);

  useEffect(() => {
    const fetchAllData = async () => {
      try {
        const [projRes, skillRes, profRes] = await Promise.all([
          fetch('http://localhost:8081/api/v1/projects'),
          fetch('http://localhost:8081/api/v1/skills'),
          fetch('http://localhost:8081/api/v1/profile')
        ]);
        const projData = await projRes.json();
        const skillData = await skillRes.json();
        const profData = await profRes.json();

        if (projData.success) setProjects(projData.data);
        if (skillData.success) setSkills(skillData.data);
        if (profData.success && profData.data.length > 0) setProfile(profData.data[0]);

        setIsLoading(false);
      } catch (error) { 
        console.error("Veri çekme hatası:", error); 
        setIsLoading(false); 
      }
    };
    fetchAllData();
  }, []);

  const getSkillsByCategory = (cat) => {
    return skills
      .filter(s => {
        const skillCat = s.category || s.categoryName || "";
        return skillCat.trim().toLowerCase() === cat.toLowerCase();
      })
      .sort((a, b) => (a.orderIndex || 0) - (b.orderIndex || 0));
  };

  const handleSmoothScroll = (e, targetId) => {
    e.preventDefault();
    const target = document.getElementById(targetId);
    if (!target) return;
    const startPosition = window.scrollY;
    const targetPosition = target.getBoundingClientRect().top + startPosition;
    const distance = targetPosition - startPosition;
    const duration = 1500;
    let start = null;
    window.requestAnimationFrame(function step(timestamp) {
      if (!start) start = timestamp;
      const progress = timestamp - start;
      const ease = progress < duration / 2 ? 8 * Math.pow(progress / duration, 4) : 1 - Math.pow(-2 * progress / duration + 2, 4) / 2;
      window.scrollTo(0, startPosition + distance * ease);
      if (progress < duration) window.requestAnimationFrame(step);
    });
  };

  const SectionDivider = () => (
    <div className="relative w-full flex justify-center py-12 opacity-80 pointer-events-none select-none">
      <div className="absolute top-1/2 w-[70%] max-w-4xl h-[1px] bg-gradient-to-r from-transparent via-[#8b5cf6]/30 to-transparent"></div>
      <div className="absolute top-1/2 -translate-y-1/2 w-[40%] max-w-xl h-24 bg-[#6b21a8] filter blur-[80px] opacity-[0.12] rounded-full mix-blend-screen"></div>
    </div>
  );

  return (
    <div id="top" className="min-h-screen bg-[#050208] text-[#E2DCE7] selection:bg-[#6b21a8] selection:text-white font-sans relative overflow-x-hidden">
      
      <div className="absolute top-[-10%] left-[20%] w-[60%] h-[80%] bg-[conic-gradient(from_0deg,transparent_0_180deg,#6b21a8_360deg)] rounded-full mix-blend-screen filter blur-[150px] opacity-[0.10] animate-[spin_12s_linear_infinite] pointer-events-none"></div>

      {/* HEADER */}
      <header className="fixed top-0 left-0 right-0 z-50 flex justify-center pt-2 transition-all duration-1000">
        <nav className={`relative flex items-center justify-between transition-all duration-[1200ms] ${isScrolled ? 'w-[90%] max-w-[850px] px-8 py-3.5 mt-4 rounded-full shadow-[0_0_30px_-10px_rgba(107,33,168,0.15)]' : 'w-full max-w-7xl px-6 py-8 mt-0'}`}>
          <div className={`absolute inset-0 overflow-hidden transition-opacity duration-1000 ${isScrolled ? 'opacity-100 rounded-full' : 'opacity-0'}`}>
            <div className="absolute top-1/2 left-1/2 w-[2000px] h-[2000px] -translate-x-1/2 -translate-y-1/2 bg-[conic-gradient(from_0deg,transparent_0_280deg,#8b5cf6_360deg)] opacity-20 animate-[spin_4s_linear_infinite]" />
            <div className="absolute inset-[1px] bg-[#0A0510]/95 backdrop-blur-xl rounded-full" />
          </div>
          <div className="relative z-10 flex items-center justify-between w-full">
            <a href="#top" onClick={(e) => handleSmoothScroll(e, 'top')} className="shrink-0 cursor-pointer flex items-center">
              <img src={myLogo} alt="Logo" className="h-8 md:h-10 object-contain drop-shadow-[0_0_15px_rgba(255,255,255,0.1)] transition-transform hover:scale-105" />
            </a>
            
            <div className="hidden md:flex items-center space-x-10">
              <a href="#about" onClick={(e) => handleSmoothScroll(e, 'about')} className="text-sm font-medium text-[#8c8496] hover:text-white transition-colors cursor-pointer">{t.nav.about}</a>
              <a href="#skills" onClick={(e) => handleSmoothScroll(e, 'skills')} className="text-sm font-medium text-[#8c8496] hover:text-white transition-colors cursor-pointer">{t.nav.skills}</a>
              <a href="#projects" onClick={(e) => handleSmoothScroll(e, 'projects')} className="text-sm font-medium text-[#8c8496] hover:text-white transition-colors cursor-pointer">{t.nav.projects}</a>
            </div>

            <div className="flex items-center space-x-6 shrink-0">
              <div className="group relative text-sm font-medium tracking-widest text-[#8c8496] transition-colors cursor-pointer flex items-center gap-2 py-2">
                <svg className="w-4 h-4 group-hover:text-white transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M21 12a9 9 0 01-9 9m9-9a9 9 0 00-9-9m9 9H3m9 9a9 9 0 01-9-9m9 9c1.657 0 3-4.03 3-9s-1.343-9-3-9m0 18c-1.657 0-3-4.03-3-9s1.343-9 3-9m-9 9a9 9 0 019-9"></path></svg>
                <span className="group-hover:text-white transition-colors uppercase">{lang}</span> 
                <span className="text-[10px] group-hover:text-white transition-colors">▼</span>
                <div className="absolute top-[100%] right-0 mt-3 w-20 bg-[#0A0510]/95 backdrop-blur-xl border border-white/10 rounded-xl shadow-2xl opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-300 flex flex-col overflow-hidden transform group-hover:translate-y-0 translate-y-2">
                  <div onClick={() => setLang('TR')} className={`px-4 py-3 transition-colors text-center text-xs font-bold uppercase ${lang === 'TR' ? 'text-[#8b5cf6] bg-white/5' : 'text-white hover:bg-white/10'}`}>TR</div>
                  <div onClick={() => setLang('EN')} className={`px-4 py-3 transition-colors text-center text-xs font-bold uppercase ${lang === 'EN' ? 'text-[#8b5cf6] bg-white/5' : 'text-white hover:bg-white/10'}`}>EN</div>
                </div>
              </div>
              <a href="#contact" onClick={(e) => handleSmoothScroll(e, 'contact')} className="hidden sm:flex items-center gap-2 bg-white/5 border border-white/10 text-[#E2DCE7] text-sm font-medium py-2 px-6 rounded-full transition-all group backdrop-blur-sm cursor-pointer hover:bg-white/10">{t.nav.contact}</a>
            </div>
          </div>
        </nav>
      </header>

      {/* HERO SECTION */}
      <section className="relative z-10 max-w-7xl mx-auto px-6 pt-40 md:pt-52 pb-24 flex flex-col justify-center min-h-[90vh]">
        <p className="text-[#8c8496] font-mono mb-6 tracking-widest uppercase text-xs md:text-sm">
          {/* FIX: Dil EN ise ve titleEn doluysa İngilizcesini bas, yoksa Türkçesini bas */}
          {profile?.fullName || "Yusuf Ülgen"} — {lang === 'EN' && profile?.titleEn ? profile.titleEn : (profile?.title || "Full Stack & Mobil Developer")}
        </p>
        <h1 className="text-5xl md:text-7xl lg:text-[5.5rem] font-extrabold tracking-tight mb-8 leading-[1.05] text-[#F8F7F9]">
          {t.hero.subtitle}
        </h1>
        <p className="text-[#928b9c] text-lg md:text-xl max-w-2xl mb-12 leading-relaxed font-light italic">
          {lang === 'EN' ? "I design experiences that take your brand one step further digitally." : "Markanızı dijitalde bir adım öteye taşıyacak deneyimler tasarlıyorum."}
        </p>
        <a href="#projects" onClick={(e) => handleSmoothScroll(e, 'projects')} className="inline-flex items-center justify-center px-10 py-3.5 text-[13px] font-medium text-white border border-white/10 rounded-full hover:bg-white/5 transition-all duration-300 w-fit cursor-pointer tracking-widest uppercase">
          {t.hero.explore}
        </a>
      </section>

      <SectionDivider />

      {/* HAKKIMDA SECTION */}
      <section id="about" className="relative z-10 max-w-7xl mx-auto px-6 py-24 flex flex-col md:flex-row items-center gap-16">
        <div className="flex-1">
          <h2 className="text-xs font-mono text-[#7a7085] tracking-[0.3em] uppercase mb-6 italic font-bold">{t.about.tag}</h2>
          <h3 className="text-3xl md:text-5xl font-extrabold tracking-tighter leading-tight mb-8 text-[#F8F7F9]">
            {t.about.title}
          </h3>
          <p className="text-[#928b9c] text-lg leading-relaxed font-light mb-8 max-w-xl whitespace-pre-line">
            {/* FIX: Veritabanındaki Bio'yu dil seçeneğine göre bas */}
            {lang === 'EN' && profile?.bioEn ? profile.bioEn : (profile?.bio || "Sistem mimarisinin o sarsılmaz yapısını kuruyorum.")}
          </p>
        </div>
        <div className="flex-1 w-full relative grid grid-cols-1 sm:grid-cols-2 gap-4">
          <div className="bg-white/[0.02] border border-white/5 p-8 rounded-2xl hover:border-white/10 transition-all backdrop-blur-md">
            <div className="text-4xl font-black text-transparent bg-clip-text bg-gradient-to-b from-white to-[#50455e] mb-4">01</div>
            <div className="text-sm font-medium mb-2 text-[#E2DCE7]">{t.about.archTitle}</div>
            <div className="text-xs text-[#7a7085]">{t.about.archDesc}</div>
          </div>
          <div className="bg-white/[0.02] border border-white/5 p-8 rounded-2xl hover:border-white/10 transition-all backdrop-blur-md sm:translate-y-8">
            <div className="text-4xl font-black text-transparent bg-clip-text bg-gradient-to-b from-white to-[#50455e] mb-4">02</div>
            <div className="text-sm font-medium mb-2 text-[#E2DCE7]">{t.about.uiTitle}</div>
            <div className="text-xs text-[#7a7085]">{t.about.uiDesc}</div>
          </div>
        </div>
      </section>

      <SectionDivider />

      {/* YETENEKLER SECTION */}
      <section id="skills" className="relative z-10 max-w-7xl mx-auto px-6 py-24">
        <div className="mb-20">
          <h2 className="text-xs font-mono text-[#7a7085] tracking-[0.3em] uppercase mb-4 flex items-center gap-4">{t.skills.tag} <span className="w-12 h-[1px] bg-[#7a7085]"></span></h2>
          <h3 className="text-3xl md:text-5xl font-extrabold tracking-tighter text-[#F8F7F9]">{t.skills.title}</h3>
        </div>
        <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-4 gap-6">
          {['Backend', 'Frontend', 'Mobil', 'Genel'].map((cat, idx) => (
            <div key={cat} className={`group bg-white/[0.02] border border-white/5 p-8 rounded-3xl hover:border-[#8b5cf6]/30 transition-all duration-500 hover:-translate-y-2 backdrop-blur-md ${idx % 2 !== 0 ? 'md:translate-y-8' : ''}`}>
              <div className="text-6xl font-black text-transparent bg-clip-text bg-gradient-to-b from-white to-[#50455e] mb-6 opacity-40 group-hover:opacity-100 transition-opacity">0{idx + 1}</div>
              <h4 className="text-xl font-bold text-[#E2DCE7] mb-3">{cat}</h4>
              <div className="flex flex-wrap gap-2">
                {getSkillsByCategory(cat).length > 0 ? (
                  getSkillsByCategory(cat).map(s => (
                    <span key={s.id} className="text-xs font-medium text-[#c4b8d1] bg-white/5 border border-white/10 px-3 py-1.5 rounded-full">{s.name}</span>
                  ))
                ) : (
                  <span className="text-[10px] text-[#50455e] uppercase tracking-widest font-mono">{t.skills.noData}</span>
                )}
              </div>
            </div>
          ))}
        </div>
      </section>

      <SectionDivider />

      {/* PROJELER SECTION */}
      <section id="projects" className="relative z-10 max-w-[105rem] mx-auto px-6 py-24">
        <h3 className="text-3xl md:text-5xl font-extrabold tracking-tighter text-[#F8F7F9] text-center mb-20 italic">{t.projects.title}</h3>
        <div className="flex flex-wrap justify-center gap-8">
          {isLoading ? (
            <div className="text-[#8c8496] font-mono text-xl animate-pulse w-full text-center py-20">Loading...</div>
          ) : (
            projects.map((project) => {
              const techArray = project.technologies ? project.technologies.split(',').map(t => t.trim()) : [];
              return (
                <div key={project.id} className="group bg-white/[0.02] border border-white/5 rounded-3xl overflow-hidden hover:border-[#8b5cf6]/30 transition-all duration-500 flex flex-col w-full lg:w-[calc(50%-1rem)]">
                  <div className="h-64 md:h-80 bg-[#0A0510] relative flex items-center justify-center text-[#50455e] font-mono overflow-hidden">
                    <span className="opacity-50 text-4xl">🖼️</span>
                    <p className="absolute bottom-4 uppercase tracking-[0.2em] text-[10px]">
                      {/* Küçük resim altı başlık */}
                      {lang === 'EN' && project.titleEn ? project.titleEn : project.title}
                    </p>
                  </div>
                  <div className="p-8 md:p-12">
                    {/* Kart Başlığı */}
                    <h4 className="text-2xl font-bold text-[#E2DCE7] mb-3">
                      {lang === 'EN' && project.titleEn ? project.titleEn : project.title}
                    </h4>
                    {/* Kart Açıklaması */}
                    <p className="text-[#928b9c] text-sm leading-relaxed mb-8 line-clamp-3 font-light">
                      {lang === 'EN' && project.descriptionEn ? project.descriptionEn : project.description}
                    </p>
                    <button onClick={() => setSelectedProject({ ...project, techArray })} className="flex items-center gap-2 text-white font-medium hover:text-[#8b5cf6] transition-colors group/btn cursor-pointer">{t.projects.inspect}</button>
                  </div>
                </div>
              );
            })
          )}
        </div>
      </section>

      <SectionDivider />

      {/* İLETİŞİM SECTION */}
      <section id="contact" className="relative z-10 max-w-7xl mx-auto px-6 pt-24 pb-12 flex flex-col items-center text-center">
        <h2 className="text-xs font-mono text-[#7a7085] tracking-[0.3em] uppercase mb-6 italic">{t.contact.tag}</h2>
        <h3 className="text-4xl md:text-6xl font-extrabold tracking-tighter text-[#F8F7F9] mb-6">{t.contact.title1} <br/><span className="text-transparent bg-clip-text bg-gradient-to-r from-[#bca8ce] to-[#6d5b7e]">{t.contact.title2}</span></h3>
        <p className="text-[#928b9c] text-lg max-w-2xl mb-12 font-light">{t.contact.desc}</p>
        <a href="https://wa.me/905325852579" target="_blank" rel="noreferrer" className="group relative inline-flex items-center justify-center px-10 py-5 text-base font-semibold text-white transition-all bg-white/[0.03] border border-white/10 rounded-full hover:bg-white/[0.08] hover:border-[#25D366]/40 hover:shadow-[0_0_40px_rgba(37,211,102,0.15)] w-fit cursor-pointer mb-20 hover:-translate-y-1">
          <svg className="w-6 h-6 mr-3 text-[#25D366] group-hover:scale-110 transition-transform" fill="currentColor" viewBox="0 0 24 24"><path d="M12.031 0C5.385 0 0 5.385 0 12.031c0 2.646.848 5.105 2.454 7.228L.484 24l4.904-1.921c2.046 1.433 4.497 2.193 7.127 2.193 6.646 0 12.031-5.385 12.031-12.031S18.677 0 12.031 0zm0 22.062c-2.316 0-4.526-.641-6.44-1.854l-.462-.288-3.328 1.304 1.328-3.23-.308-.49c-1.341-2.12-2.047-4.57-2.047-7.14 0-5.518 4.49-10.008 10.008-10.008 5.518 0 10.008 4.49 10.008 10.008 0 5.518-4.49 10.008-10.008 10.008zm5.5-7.513c-.302-.151-1.787-.881-2.064-.981-.277-.101-.478-.151-.679.151-.202.302-.78 1.008-.956 1.21-.176.202-.352.227-.654.076-1.554-.78-2.673-1.428-3.69-3.208-.176-.302.176-.277.453-.83.076-.151.038-.277-.038-.428-.076-.151-.679-1.637-.932-2.242-.252-.58-.504-.679-.504h-.58c-.202 0-.528.076-.805.378-.277.302-1.057 1.032-1.057 2.518s1.082 2.922 1.233 3.123c.151.202 2.14 3.258 5.181 4.568.73.302 1.304.478 1.748.604.73.202 1.396.176 1.921.101.593-.076 1.787-.73 2.04-1.435.252-.705.252-1.309.176-1.435-.075-.126-.276-.201-.578-.352z"/></svg>
          {t.contact.btn}
        </a>
        <div className="flex flex-wrap justify-center gap-10 md:gap-16 border-t border-white/5 pt-12 w-full max-w-2xl text-[#8c8496] text-sm font-medium tracking-wide">
           <a href={profile?.githubLink || "#"} target="_blank" rel="noreferrer" className="hover:text-white transition-colors">GitHub ↗</a>
           <a href={profile?.linkedinLink || "#"} target="_blank" rel="noreferrer" className="hover:text-white transition-colors">LinkedIn ↗</a>
           <a href={profile?.email ? `mailto:${profile.email}` : "#"} className="hover:text-white transition-colors">E-Posta ↗</a>
        </div>
        <div className="mt-16 text-[#50455e] text-[10px] tracking-widest font-mono italic">© {new Date().getFullYear()} {(profile?.fullName || "YUSUF ÜLGEN").toUpperCase()}. {t.contact.rights}</div>
      </section>

      {/* MODAL SECTION */}
      {selectedProject && (
        <div className="fixed inset-0 z-[100] flex items-center justify-center p-4">
          <div className="absolute inset-0 bg-[#050208]/80 backdrop-blur-xl cursor-pointer" onClick={() => setSelectedProject(null)}></div>
          <div className="relative w-full max-w-5xl max-h-[90vh] overflow-y-auto bg-[#0A0510] border border-white/10 rounded-3xl shadow-2xl p-8 md:p-16 animate-[fadeInUp_0.4s_ease-out]">
            <button onClick={() => setSelectedProject(null)} className="absolute top-6 right-6 text-2xl hover:rotate-90 transition-all cursor-pointer text-white">✕</button>
            <h2 className="text-4xl md:text-6xl font-black text-[#F8F7F9] mb-4 uppercase">
              {lang === 'EN' && selectedProject.titleEn ? selectedProject.titleEn : selectedProject.title}
            </h2>
            {selectedProject.duration && <p className="text-emerald-400 font-mono text-sm mb-8 tracking-widest uppercase italic font-bold">{t.modal.devTime} {selectedProject.duration}</p>}
            <p className="text-[#928b9c] text-lg leading-relaxed mb-12 whitespace-pre-line font-light">
              {lang === 'EN' && selectedProject.descriptionEn ? selectedProject.descriptionEn : selectedProject.description}
            </p>
            <div className="flex flex-wrap gap-2 mb-12">
              {selectedProject.techArray.map((tech, i) => (<span key={i} className="text-xs font-medium text-[#E2DCE7] bg-white/10 border border-white/10 px-4 py-2 rounded-lg">{tech}</span>))}
            </div>
            <div className="flex gap-4">
              {selectedProject.liveUrl && <a href={selectedProject.liveUrl} target="_blank" rel="noreferrer" className="bg-white text-black px-8 py-3 rounded-full font-bold transition-transform hover:scale-105">{t.modal.liveSite}</a>}
              {selectedProject.githubUrl && <a href={selectedProject.githubUrl} target="_blank" rel="noreferrer" className="bg-white/5 border border-white/10 text-white px-8 py-3 rounded-full font-bold transition-transform hover:scale-105">{t.modal.github}</a>}
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default Home;