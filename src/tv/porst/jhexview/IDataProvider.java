package tv.porst.jhexview;

public interface IDataProvider {

	void addListener(final IDataChangedListener hexView);

	void removeListener(IDataChangedListener listener);

	byte[] getData();

	byte[] getData(long offset, long length);

	long getDataLength();

	void setData(long offset, byte[] data);

	boolean hasData(long start, long length);

	boolean isEditable();

	boolean keepTrying();
}
